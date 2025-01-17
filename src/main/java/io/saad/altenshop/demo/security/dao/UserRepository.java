package io.saad.altenshop.demo.security.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.saad.altenshop.demo.security.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	//Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String username);
}
