package com.projectjavasneaker.backendis216.services;

import com.projectjavasneaker.backendis216.exception.NotEnoughProductsInStockException;
import com.projectjavasneaker.backendis216.models.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface ShoppingCartService {
    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    void checkout() throws NotEnoughProductsInStockException;

    BigDecimal getTotal();
}
