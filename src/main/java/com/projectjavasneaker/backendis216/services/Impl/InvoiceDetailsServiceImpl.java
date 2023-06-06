package com.projectjavasneaker.backendis216.services.Impl;

import com.projectjavasneaker.backendis216.Exception.NotFoundException;
import com.projectjavasneaker.backendis216.models.Invoice;
import com.projectjavasneaker.backendis216.models.InvoiceDetails;
import com.projectjavasneaker.backendis216.models.User;
import com.projectjavasneaker.backendis216.repository.InvoiceDetailsRepository;
import com.projectjavasneaker.backendis216.repository.UserRepository;
import com.projectjavasneaker.backendis216.services.InvoiceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class InvoiceDetailsServiceImpl implements InvoiceDetailsService {
    @Autowired
    private InvoiceDetailsRepository invoiceDetailsRepository;
    @Autowired
    private UserRepository userRepository;
    public List<InvoiceDetails> getInvoiceDetailsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        List<Invoice> invoices = user.getInvoices();
        List<InvoiceDetails> purchasedProducts = new ArrayList<>();
        for (Invoice invoice : invoices) {
            purchasedProducts.addAll(invoice.getInvoiceDetails());
        }
        return purchasedProducts;
    }

}
