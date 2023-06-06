package com.projectjavasneaker.backendis216.controllers;


import com.projectjavasneaker.backendis216.models.Invoice;
import com.projectjavasneaker.backendis216.models.InvoiceDetails;
import com.projectjavasneaker.backendis216.models.User;
import com.projectjavasneaker.backendis216.payload.response.PageResponse;
import com.projectjavasneaker.backendis216.payload.response.ResponseObject;
import com.projectjavasneaker.backendis216.repository.UserRepository;
import com.projectjavasneaker.backendis216.services.InvoiceDetailsService;
import com.projectjavasneaker.backendis216.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private InvoiceDetailsService invoiceDetailsService;
    @Autowired
    private InvoiceService invoiceService;
    @GetMapping("/all-user")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<ResponseObject> getAllUsers(){
        List<User> users = userRepository.findAll();
        if(users.size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","get all products successfully", users)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("ok", "cannot find product", "")
        );
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<PageResponse> getAllUsersPage(@RequestParam Optional<Integer> page){
       Page<User> pageUsers = userRepository.findAll(PageRequest.of(page.orElse(0), 6));
       return ResponseEntity.status(HttpStatus.OK).body(
               new PageResponse(page, pageUsers.getSize(), pageUsers.getTotalElements(),
                       pageUsers.getTotalPages(),
                       pageUsers.getContent()
                       )
       );
    }

//    sua
//@PutMapping("/{id}")
//@PreAuthorize("hasRole('ADMIN')")
//ResponseEntity<ResponseObject> UpdateProduct(@PathVariable Long id, @RequestBody User newUser){
//    User existUser = userRepository.findById(id).map((user)->{
//        user.setAddress(newUser.getAddress());
//        user.setBirth(newUser.getBirth());
//        user.setGender(newUser.getGender());
//        user.setPhone(newUser.getPhone());
//        return userRepository.save(user);
//    }).orElseGet(()->{
//        return userRepository.save(newUser);
//    });
//
//    return ResponseEntity.status(HttpStatus.OK).body(
//            new ResponseObject("ok", "updated product success", existUser)
//    );
//}

//    xoa
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<ResponseObject> DeleteProduct(@PathVariable Long id){
        boolean existProduct = userRepository.existsById(id);
        if(existProduct){
            userRepository.deleteById(id);
            List<User> users = userRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete product successfully", users)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "cannot find product to delete", "")
        );
    }
    // lấy ra danh sách sản phẩm user đã mua
    @GetMapping("/{userId}/list-product")
    public ResponseEntity<List<InvoiceDetails>> getPurchasedProducts(@PathVariable Long userId) {
        List<InvoiceDetails> purchasedProducts = invoiceDetailsService.getInvoiceDetailsByUserId(userId);
        return ResponseEntity.ok(purchasedProducts);
    }
    @GetMapping("/{userId}/all-invoices")
    public ResponseEntity<List<Invoice>> getUserInvoices(@PathVariable Long userId) {
        List<Invoice> invoices = invoiceService.getInvoicesByUserId(userId);
        return ResponseEntity.ok(invoices);
    }
}
