package com.projectjavasneaker.backendis216.services;

import com.projectjavasneaker.backendis216.models.InvoiceDetails;

import java.util.List;

public interface InvoiceDetailsService {
    public List<InvoiceDetails> getInvoiceDetailsByUserId(Long userId);
}
