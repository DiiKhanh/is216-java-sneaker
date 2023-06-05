package com.projectjavasneaker.backendis216.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "INVOICE")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INVOICEID")
    private Long invoiceId;


    @ManyToOne
    @JoinColumn(name="user_id", nullable = false, referencedColumnName = "id")
    private User users;

    private String status;

    private Date orderDate;

    private String shipAddress;

    private BigDecimal totalPrice;
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceDetails> invoiceDetails;




    public Invoice() {
    }

    public Invoice(Long invoiceId, Long userId, String status, Date orderDate, String shipAddress, BigDecimal totalPrice) {
        this.invoiceId = invoiceId;
        this.status = status;
        this.orderDate = orderDate;
        this.shipAddress = shipAddress;
        this.totalPrice = totalPrice;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}

