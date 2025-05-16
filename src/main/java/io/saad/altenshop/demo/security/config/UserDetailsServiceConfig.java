package io.saad.altenshop.demo.security.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import io.saad.altenshop.demo.entity.AppUser;
import io.saad.altenshop.demo.repository.UserRepository;

@Configuration
public class UserDetailsServiceConfig {

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository) {
        return email -> {
            AppUser appUser = userRepository.findByEmail(email)
            		.orElseThrow(() ->  new UsernameNotFoundException("Cannot find the user"));
            
            return new User(appUser.getEmail(), appUser.getPassword(), new ArrayList<>());
        };
    }
}