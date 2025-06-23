package com.app.FundTheStory.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class PaymentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private double amount;

    private String orderId;

    private String paymentId;

    private String status;

    private LocalDateTime createdAt;

    private String campaign;

    public PaymentOrder(){};


    public PaymentOrder(Long id, String name, String email, String phone, double amount, String orderId, String paymentId, String status, LocalDateTime createdAt,String campaign) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.amount = amount;
        this.orderId = orderId;
        this.paymentId = paymentId;
        this.status = status;
        this.createdAt = createdAt;
        this.campaign = campaign;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public double getAmount() {
        return amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String isStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
