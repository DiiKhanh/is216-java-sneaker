package com.projectjavasneaker.backendis216.controllers;

import com.projectjavasneaker.backendis216.models.Cart;
import com.projectjavasneaker.backendis216.models.CartDetails;
import com.projectjavasneaker.backendis216.models.Product;
import com.projectjavasneaker.backendis216.payload.request.CartRequest;
import com.projectjavasneaker.backendis216.services.CartDetailsService;
import com.projectjavasneaker.backendis216.services.CartService;
import com.projectjavasneaker.backendis216.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }


    @PostMapping("/{cartId}/addProduct")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addProductToCart(@PathVariable Long cartId,
                                              @RequestBody CartRequest request) {
        cartService.addProductToCart(cartId, request.getProductId(), request.getQuantity());
        return ResponseEntity.ok("Product added to cart successfully.");
    }

    @DeleteMapping("/{cartId}/products/{productId}")  // Xóa sản phẩm khỏi giỏ hàng
    public ResponseEntity<?> removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.removeProductFromCart(cartId, productId);
        return ResponseEntity.ok("Product removed from cart");
    }

    @GetMapping("/{cartId}/products")        // Lấy thông tin giỏ hàng
    public ResponseEntity<List<CartDetails>> getCart(@PathVariable Long cartId) {
        List<CartDetails> cartDetails = cartService.getAllCartDetails(cartId);
        return ResponseEntity.ok(cartDetails);
    }

//    @GetMapping("/{cartId}/getCart")        // Lấy thông tin giỏ hàng
//    public Cart getCart(@PathVariable Long cartId) {
//        return this.cartService.getCartById(cartId);
//    }


//    @GetMapping("/{cartId}/products") // Lấy thông tin giỏ hàng
//    public ResponseEntity<List<CartDetails>> getCart(@PathVariable Long cartId) {
//        Cart cart = cartService.getCartById(cartId);
//        List<CartDetails> cartDetails = cart.getAllCartDetails();
//        return ResponseEntity.ok(cartDetails);
//    }

    @PostMapping("/{cartId}/products/{productId}/increase")         // Tăng số lượng sản phẩm
    public ResponseEntity<?> increaseCartItemQuantity(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.increaseCartItemQuantity(cartId, productId);
        return ResponseEntity.ok("Cart item quantity increased");
    }

    @PostMapping("/{cartId}/products/{productId}/decrease")     // Giảm số lượng sản phẩm
    public ResponseEntity<?> decreaseCartItemQuantity(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.decreaseCartItemQuantity(cartId, productId);
        return ResponseEntity.ok("Cart item quantity decreased");
    }

    // Xuất hóa đơn
    @PostMapping("/{cartId}/checkout")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> checkout(@PathVariable Long cartId) {
        cartService.createInvoiceFromCart(cartId);
        //cartService.clearCart(cartId); // Xóa tất cả sản phẩm đã có trong giỏ hàng sau khi thanh toán
        return ResponseEntity.ok("Checkout successful");
    }
}
