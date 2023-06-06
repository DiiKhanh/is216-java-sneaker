package com.projectjavasneaker.backendis216.services;

import com.projectjavasneaker.backendis216.models.Cart;
import com.projectjavasneaker.backendis216.models.Invoice;

import java.util.List;

public interface InvoiceService {
    Invoice createInvoice();
    void saveInvoice(Invoice invoice);
    void createInvoiceDetails(Invoice invoice, Cart cart);
    public void updatePaymentStatus(Long invoiceId, String paymentStatus);
    public List<Invoice> getInvoicesByUserId(Long userId);
}
