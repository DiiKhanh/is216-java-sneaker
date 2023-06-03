package com.projectjavasneaker.backendis216.repository;

import com.projectjavasneaker.backendis216.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository <Cart, Long>{
    Optional<Cart> findById(Long cartId);
    Optional<Cart> findByUserId(Long userId);
}