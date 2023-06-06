package com.projectjavasneaker.backendis216.services;

import com.projectjavasneaker.backendis216.Exception.NotFoundException;
import com.projectjavasneaker.backendis216.models.Cart;
import com.projectjavasneaker.backendis216.models.CartDetails;
import com.projectjavasneaker.backendis216.models.Product;
import com.projectjavasneaker.backendis216.repository.CartRepository;
import com.projectjavasneaker.backendis216.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void addProductToCart(Long cartId, Long productId, int quantity) {
        // Lấy thông tin giỏ hàng từ cơ sở dữ liệu
        System.out.println("cartId: " + cartId);
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("Cart not found"));

        // Lấy thông tin sản phẩm từ cơ sở dữ liệu
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
        BigDecimal productPrice = product.getProductPrice();

        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        boolean productExists = false;
        for (CartDetails cartDetail : cart.getCartDetails()) {
            if (cartDetail.getProduct().getId().equals(productId)) {
                // Sản phẩm đã tồn tại trong giỏ hàng, tăng giá trị quantity
                int newQuantity = cartDetail.getQuantity() + 1;
                cartDetail.setQuantity(newQuantity);
                cartDetail.setTotal(productPrice.multiply(BigDecimal.valueOf(newQuantity)));
                productExists = true;
                break;
            }
        }

        if (!productExists){
            // Tạo một đối tượng CartDetails mới
            CartDetails cartDetail = new CartDetails();
            cartDetail.setCart(cart);
            cartDetail.setProduct(product);
            cartDetail.setQuantity(quantity);
            cartDetail.setTotal(productPrice.multiply(BigDecimal.valueOf(quantity)));
            // Thêm đối tượng CartDetails vào giỏ hàng
            cart.getCartDetails().add(cartDetail);
        }

        cart.updateTotalPrice();
        // Lưu cập nhật giỏ hàng vào cơ sở dữ liệu
        cartRepository.save(cart);
    }

    @Override
    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("Cart not found"));
    }
    @Override
    public List<CartDetails> getAllCartDetails(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("Cart not found"));
        return new ArrayList<>(cart.getCartDetails());
    }
    @Override
    public void removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("Cart not found"));

        cart.getCartDetails().removeIf(cartDetail -> cartDetail.getProduct().getId().equals(productId));

        cart.updateTotalPrice();
        cartRepository.save(cart);
    }
    @Override
    public void increaseCartItemQuantity(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("Cart not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
        for (CartDetails cartDetail : cart.getCartDetails()) {
            if (cartDetail.getProduct().getId().equals(productId)) {
                int newQuantity = cartDetail.getQuantity() + 1;
                cartDetail.setQuantity(newQuantity);
                cartDetail.setTotal(product.getProductPrice().multiply(BigDecimal.valueOf(newQuantity)));
                break;
            }
        }

        cart.updateTotalPrice();
        cartRepository.save(cart);
    }
    @Override
    public void decreaseCartItemQuantity(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("Cart not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));

        for (CartDetails cartDetail : cart.getCartDetails()) {
            if (cartDetail.getProduct().getId().equals(productId)) {
                int newQuantity = cartDetail.getQuantity() - 1;
                if (newQuantity > 0) {
                    cartDetail.setQuantity(newQuantity);
                    cartDetail.setTotal(product.getProductPrice().multiply(BigDecimal.valueOf(newQuantity)));

                } else {
                    cart.getCartDetails().remove(cartDetail);
                }
                break;
            }
        }

        cart.updateTotalPrice();
        cartRepository.save(cart);
    }
}
