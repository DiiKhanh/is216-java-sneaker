package com.projectjavasneaker.backendis216.controllers;


import com.projectjavasneaker.backendis216.models.Product;
import com.projectjavasneaker.backendis216.payload.response.ResponseObject;
import com.projectjavasneaker.backendis216.repository.ProductRepository;
import com.projectjavasneaker.backendis216.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @GetMapping("/all")
    ResponseEntity<ResponseObject> GetProducts(){
        List<Product> product = productService.getAllProducts();
        if(product != null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","get all products successfully", product)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("ok", "cannot find product", "")
        );

    }

    //    add product
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<ResponseObject> AddProduct(@RequestBody Product product){
        Product existProduct = productService.addNewProduct(product);
        if(existProduct == null){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "product name has already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "product added successfully", existProduct)
        );
    }

//    get 1 product
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> GetDetailProduct(@PathVariable Long id)  {
        Optional<Product> existProduct = productService.getProduct(id);
        return existProduct.isPresent() ?  ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "get success", existProduct)
        ) :   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", "can not find product with "+id, "")
        );

    }


    //    sua product
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<ResponseObject> UpdateProduct(@PathVariable Long id, @RequestBody Product newProduct){
        Product existProduct = productRepository.findById(id).map((product)->{
            product.setProductName(newProduct.getProductName());
            product.setDescription(newProduct.getDescription());
            product.setCategory(newProduct.getCategory());
            product.setProductPrice(newProduct.getProductPrice());
            product.setImgUrl(newProduct.getImgUrl());
            product.setQuantity(newProduct.getQuantity());
            product.setBrandName(newProduct.getBrandName());
            product.setDesigner(newProduct.getDesigner());
            return productRepository.save(product);
        }).orElseGet(()->{
            return productRepository.save(newProduct);
        });

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "updated product success", existProduct)
        );
    }
    //    delete 1 product
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<ResponseObject> DeleteProduct(@PathVariable Long id){
        boolean existProduct = productRepository.existsById(id);
        if(existProduct){
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete product successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "cannot find product to delete", "")
        );
    }
}
