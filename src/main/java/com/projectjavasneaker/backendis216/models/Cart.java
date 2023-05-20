package com.projectjavasneaker.backendis216.models;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long productID;
    private int quantity;

    public Cart() {
    }

    public Cart(Long id, Long userId, int quantity) {
        this.id = id;
        this.userId = userId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
