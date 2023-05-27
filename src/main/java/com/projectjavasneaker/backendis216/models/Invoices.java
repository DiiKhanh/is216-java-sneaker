package com.projectjavasneaker.backendis216.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "invoices")
public class Invoices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InvoiceId")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User users;

    @NotBlank
    private String status;

    @NotBlank
    private String shipaddress;


    private Double totalprice;

    @CreatedDate
    private Date orderdate;



    public Invoices(Long id, User users, @NotBlank String status, @NotBlank String shipaddress,
                    @NotBlank Double totalprice, Date orderdate) {
        this.id = id;
        this.users = users;
        this.status = status;
        this.shipaddress = shipaddress;
        this.totalprice = totalprice;
        this.orderdate = orderdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShipaddress() {
        return shipaddress;
    }

    public void setShipaddress(String shipaddress) {
        this.shipaddress = shipaddress;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }


}