package com.projectjavasneaker.backendis216.controllers;

import com.projectjavasneaker.backendis216.models.Invoices;
import com.projectjavasneaker.backendis216.models.Product;
import com.projectjavasneaker.backendis216.payload.response.ResponseObject;
import com.projectjavasneaker.backendis216.repository.InvoiceRepository;
import com.projectjavasneaker.backendis216.repository.ProductRepository;
import com.projectjavasneaker.backendis216.services.InvoiceService;
import com.projectjavasneaker.backendis216.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import com.projectjavasneaker.backendis216.models.Invoices;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/invoices")
public class InvoiceController  {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceService invoiceService;


    @GetMapping("/all")
    ResponseEntity<ResponseObject> GetInvoices(){
        List<Invoices> invoice = invoiceService.getAllInvoices();

        if(invoice != null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","get all invoices successfully", invoice)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("ok", "cannot find invoice", "")
        );

    }

    //    add invoice
    @PostMapping("/add")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Invoices newInvoice) {
        //chua xong de khanh lam

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert Product successfully", invoiceRepository.save(newInvoice))
        );

    }

    //    get 1 product
//    @GetMapping("/{id}")
//    ResponseEntity<ResponseObject> GetDetailProduct(@PathVariable Long id)  {
//        Optional<Product> existProduct = invoiceService.getInvoice(id);
//        return existProduct.isPresent() ?  ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("ok", "get success", existProduct)
//        ) :   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                new ResponseObject("failed", "can not find product with "+id, "")
//        );
//
//    }
}