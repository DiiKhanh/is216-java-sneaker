package com.projectjavasneaker.backendis216.services.Impl;

import com.projectjavasneaker.backendis216.models.Product;
import com.projectjavasneaker.backendis216.repository.ProductRepository;
import com.projectjavasneaker.backendis216.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addNewProduct(Product product) {
        Product existProduct = productRepository.findByProductName(product.getProductName().trim());
        if(existProduct != null){
            return null;
        }

        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }
    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
