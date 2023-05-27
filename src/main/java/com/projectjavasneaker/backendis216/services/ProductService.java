package com.projectjavasneaker.backendis216.services;

import com.projectjavasneaker.backendis216.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(Long id);

    Page<Product> findAllProductsPageable(Pageable pageable);

    public List<Product> getAllProducts();

    public Product addNewProduct(Product product);
    public Optional<Product> getProduct(Long id);

}
