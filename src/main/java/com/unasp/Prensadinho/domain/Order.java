package com.unasp.Prensadinho.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.unasp.Prensadinho.domain.ENUM.PaymentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> items = new ArrayList<>();

    @Positive
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "spun_id")
    @JsonBackReference
    private Spun spun;

    public Order() {
    }

    public Order(Long id, List<OrderItem> items, BigDecimal value, PaymentType paymentType, Spun spun) {
        this.id = id;
        this.items = items;
        this.value = value;
        this.paymentType = paymentType;
        this.spun = spun;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Spun getSpun() {
        return spun;
    }

    public void setSpun(Spun spun) {
        this.spun = spun;
    }
}
