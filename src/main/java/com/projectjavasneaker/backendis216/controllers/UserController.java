package com.projectjavasneaker.backendis216.controllers;


import com.projectjavasneaker.backendis216.models.User;
import com.projectjavasneaker.backendis216.payload.response.ResponseObject;
import com.projectjavasneaker.backendis216.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
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
}
