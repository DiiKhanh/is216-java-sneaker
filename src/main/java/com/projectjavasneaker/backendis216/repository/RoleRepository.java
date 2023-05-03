package com.projectjavasneaker.backendis216.repository;

import java.util.Optional;

import com.projectjavasneaker.backendis216.models.ERole;
import com.projectjavasneaker.backendis216.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
