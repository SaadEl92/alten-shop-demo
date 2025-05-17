package io.saad.altenshop.demo.security.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import io.saad.altenshop.demo.entity.AppUser;
import io.saad.altenshop.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final RsaKeyProperties rsaKeys;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests( requests -> 
                	requests
	                	.requestMatchers("/v3/api-docs/**").permitAll()
	    				.requestMatchers("/swagger-ui/**").permitAll()
	            		.requestMatchers("/api/v0/auth/**").permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .oauth2ResourceServer( oauth2 -> 
                	oauth2
                		.jwt(Customizer.withDefaults())
                )
                .sessionManagement(session -> 
                	session
                		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .build();
    }
    
    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository) {
        return email -> {
            AppUser appUser = userRepository.findByEmail(email)
            		.orElseThrow(() ->  new UsernameNotFoundException("Cannot find the user"));
            
            return new User(appUser.getEmail(), appUser.getPassword(), new ArrayList<>());
        };
    }
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    JwtDecoder jwtDecoder() {
    	NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
    	OAuth2TokenValidator<Jwt> withTimestamp = JwtValidators.createDefault();
    	OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(withTimestamp);
        jwtDecoder.setJwtValidator(validator);
    	return jwtDecoder;
    }
    
    @Bean
    JwtEncoder jwtEncoder() {
    	JWK jwt = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
    	JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwt));
    	return new NimbusJwtEncoder(jwks);
    }
    

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
