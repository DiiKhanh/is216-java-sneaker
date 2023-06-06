package com.projectjavasneaker.backendis216.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "INVOICE_DETAILS")
public class InvoiceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InvoiceDetailsId")
    private Long InvoiceDetailsId;


    @ManyToOne
    @JoinColumn(name = "INVOICEID", referencedColumnName = "INVOICEID")
    @JsonIgnore
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    private int quantity;

    private BigDecimal discount;

    private BigDecimal price;

    public InvoiceDetails() {
    }

    public Long getInvoiceDetailsId() {
        return InvoiceDetailsId;
    }

    public void setInvoiceDetailsId(Long invoiceDetailsId) {
        InvoiceDetailsId = invoiceDetailsId;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public InvoiceDetails(Long invoiceDetailsId, Invoice invoice, Product product, Long productId, int quantity, BigDecimal discount, BigDecimal price) {
        InvoiceDetailsId = invoiceDetailsId;
        this.invoice = invoice;
        this.product = product;
        this.quantity = quantity;
        this.discount = discount;
        this.price = price;
    }
}
