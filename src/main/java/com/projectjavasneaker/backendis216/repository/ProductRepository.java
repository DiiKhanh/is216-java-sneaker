package com.projectjavasneaker.backendis216.repository;

import com.projectjavasneaker.backendis216.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductName(String name);

}
