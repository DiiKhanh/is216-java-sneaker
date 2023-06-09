package com.projectjavasneaker.backendis216.payload.request;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

public class CartRequest {
    private Long productId;



    private int quantity;

    public CartRequest(Long productId) {
        this.productId = productId;
    }
    public CartRequest(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
    public CartRequest() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
