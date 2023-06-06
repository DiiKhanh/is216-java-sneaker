package com.projectjavasneaker.backendis216.services.Impl;

import com.projectjavasneaker.backendis216.Exception.NotFoundException;
import com.projectjavasneaker.backendis216.models.*;
import com.projectjavasneaker.backendis216.repository.InvoiceDetailsRepository;
import com.projectjavasneaker.backendis216.repository.InvoiceRepository;
import com.projectjavasneaker.backendis216.repository.UserRepository;
import com.projectjavasneaker.backendis216.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceDetailsRepository invoiceDetailsRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Invoice createInvoice() {
        Invoice invoice = new Invoice();
        return invoice;
    }
    @Override
    public void saveInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }
    @Override
    public void createInvoiceDetails(Invoice invoice, Cart cart) {
        for (CartDetails cartDetail : cart.getCartDetails()) {
            // Lấy thông tin sản phẩm từ CartDetails
            Product product = cartDetail.getProduct();

            // Tạo đối tượng InvoiceDetails
            InvoiceDetails invoiceDetail = new InvoiceDetails();
            invoiceDetail.setInvoice(invoice);
            invoiceDetail.setProduct(product);
            invoiceDetail.setQuantity(cartDetail.getQuantity());
            invoiceDetail.setPrice(cartDetail.getTotal());
            // Thiết lập các thông tin khác cho invoiceDetail

            // Lưu invoiceDetail vào cơ sở dữ liệu
            invoiceDetailsRepository.save(invoiceDetail);
        }
    }
    @Override
    public void updatePaymentStatus(Long invoiceId, String paymentStatus) {
        // Lấy thông tin hóa đơn từ cơ sở dữ liệu
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new NotFoundException("Invoice not found"));

        // Cập nhật trạng thái thanh toán
        invoice.setStatus(paymentStatus);

        // Lưu cập nhật vào cơ sở dữ liệu
        invoiceRepository.save(invoice);
    }
    public List<Invoice> getInvoicesByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        return user.getInvoices();
    }

}
