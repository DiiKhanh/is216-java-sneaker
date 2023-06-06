package com.projectjavasneaker.backendis216.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARTID")
    private Long CartId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart",cascade = CascadeType.ALL)
    //@JoinColumn(name = "cart_id") // Tên cột khóa ngoại trong bảng CartDetails
    private List<CartDetails> cartDetails;


    public void updateTotalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartDetails cartDetail : cartDetails) {
            totalPrice = totalPrice.add(cartDetail.getProduct().getProductPrice().multiply(BigDecimal.valueOf(cartDetail.getQuantity())));
        }
        this.total = totalPrice;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<CartDetails> getCartDetails() {
        return cartDetails;
    }

    public Long getCartId() {
        return CartId;
    }

    public void setCartId(Long cartId) {
        CartId = cartId;
    }

    //    public List<CartDetails> getAllCartDetails() {
//        return new ArrayList<>(cartDetails);
//    }

    public void setCartDetails(List<CartDetails> cartDetails) {
        this.cartDetails = cartDetails;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private BigDecimal total; // Tổng tiền trong giỏ hàng



// sai
//    public void addProduct(Product product, int quantity) {
//        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng hay chưa
//        // Nếu đã tồn tại, tăng số lượng sản phẩm
//        // Nếu chưa tồn tại, thêm sản phẩm vào giỏ hàng
//        // ==> Giống như sơ đồ activity
//
//        // Kiểm tra sản phẩm đã tồn tại trong giỏ hàng hay chưa
//        boolean productExistsInCart = ListProductInCart.stream()
//                .anyMatch(p -> p.getId().equals(product.getId()));
//
//        if (productExistsInCart) {
//            // Sản phẩm đã tồn tại trong giỏ hàng, tăng số lượng sản phẩm
//            for (Product p : ListProductInCart) {
//                if (p.getId().equals(product.getId())) {
//                    // Tăng số lượng sản phẩm trong giỏ hàng
//                    p.setQuantity(p.getQuantity() + quantity);
//                    break;
//                }
//            }
//        } else {
//            // Sản phẩm chưa tồn tại trong giỏ hàng, thêm sản phẩm vào giỏ hàng
//            product.setQuantity(quantity);
//            ListProductInCart.add(product);
//        }
//
//        // Cập nhật tổng tiền trong giỏ hàng
//        this.total = this.total.add(product.getProductPrice().multiply(BigDecimal.valueOf(quantity)));
//    }

    public Cart() {
    }

    public Cart(Long id) {
        this.CartId = id;

    }

    public Long getId() {
        return CartId;
    }

    public void setId(Long id) {
        this.CartId = id;
    }



    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}

