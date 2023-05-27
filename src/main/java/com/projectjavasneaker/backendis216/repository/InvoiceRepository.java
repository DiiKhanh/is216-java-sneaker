package com.projectjavasneaker.backendis216.repository;

import com.projectjavasneaker.backendis216.models.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InvoiceRepository extends JpaRepository<Invoices, Long> {
//    Product findByProductName(String name);

}