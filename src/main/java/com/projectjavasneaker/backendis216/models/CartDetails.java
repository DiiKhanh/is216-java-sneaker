package com.projectjavasneaker.backendis216.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "CartDetails")
public class CartDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartDetailsID")
    private Long CartDetailsId;
    @ManyToOne
    @JoinColumn(name = "CartID", referencedColumnName = "CARTID")
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "ProductId", referencedColumnName = "id")
    private Product product;

    private int quantity;
    private BigDecimal total;

    public CartDetails() {
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CartDetails(Long cartDetailsId, Cart cart, Product product, int quantity, BigDecimal total) {
        CartDetailsId = cartDetailsId;
        this.quantity = quantity;
        this.total = total;
    }

    public Long getCartDetailsId() {
        return CartDetailsId;
    }

    public void setCartDetailsId(Long cartDetailsId) {
        CartDetailsId = cartDetailsId;
    }

//    public Cart getCart() {
//        return cart;
//    }
//
//    public void setCart(Cart cart) {
//        this.cart = cart;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
