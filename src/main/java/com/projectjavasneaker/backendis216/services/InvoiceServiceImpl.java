package com.projectjavasneaker.backendis216.services;

import com.projectjavasneaker.backendis216.models.Invoices;
import com.projectjavasneaker.backendis216.repository.InvoiceRepository;

//import com.projectjavasneaker.backendis216.repository.InvoiceRepository;
import com.projectjavasneaker.backendis216.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class InvoiceServiceImpl implements InvoiceService {


    @Autowired
    InvoiceRepository invoiceRepository;

    @Override
    public List<Invoices> getAllInvoices() {
        return invoiceRepository.findAll();
    }


    @Override
    public Optional<Invoices> getInvoice(Long id) {
        return invoiceRepository.findById(id);
    }
}