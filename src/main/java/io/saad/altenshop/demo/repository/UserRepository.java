package io.saad.altenshop.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.saad.altenshop.demo.entity.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long>{
	//Optional<User> findByUsername(String username);
	Optional<AppUser> findByEmail(String email);
}
