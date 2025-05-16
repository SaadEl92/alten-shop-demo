package io.saad.altenshop.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import io.saad.altenshop.demo.security.config.RsaKeyProperties;

@SpringBootApplication
@EnableJpaAuditing
@EnableMethodSecurity
@EnableConfigurationProperties(RsaKeyProperties.class)
public class AltenShopDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AltenShopDemoApplication.class, args);
	}

}
