package com.projectjavasneaker.backendis216.repository;

import java.util.Optional;

import com.projectjavasneaker.backendis216.models.AuthenticationType;
import com.projectjavasneaker.backendis216.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    void updateAuthenticationType(String username, AuthenticationType authType);
}
