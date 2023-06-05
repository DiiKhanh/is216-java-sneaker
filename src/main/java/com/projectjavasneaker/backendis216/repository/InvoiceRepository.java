package com.projectjavasneaker.backendis216.repository;

import com.projectjavasneaker.backendis216.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
