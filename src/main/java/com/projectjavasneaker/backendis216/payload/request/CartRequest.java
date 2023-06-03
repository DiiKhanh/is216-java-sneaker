package com.projectjavasneaker.backendis216.payload.request;

public class CartRequest {
    private Long productId;

    public CartRequest(Long productId) {
        this.productId = productId;
    }

    public CartRequest() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
