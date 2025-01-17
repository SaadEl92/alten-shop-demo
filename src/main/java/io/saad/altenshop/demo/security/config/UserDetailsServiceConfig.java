package io.saad.altenshop.demo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import io.saad.altenshop.demo.entity.User;
import io.saad.altenshop.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

@Configuration
public class UserDetailsServiceConfig {

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository) {
        return email -> {
            Optional<User> user = userRepository.findByEmail(email);
            if (!user.isPresent()) {
                throw new UsernameNotFoundException("User not found");
            }
            return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), new ArrayList<>());
        };
    }
}
