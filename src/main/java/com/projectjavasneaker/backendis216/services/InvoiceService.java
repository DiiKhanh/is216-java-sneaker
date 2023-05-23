package com.projectjavasneaker.backendis216.services;

import com.projectjavasneaker.backendis216.models.Invoices;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    public List<Invoices> getAllInvoices();

//    public Invoices addNewInvoices(Invoices invoice);
    public Optional<Invoices> getInvoice(Long id);

}
