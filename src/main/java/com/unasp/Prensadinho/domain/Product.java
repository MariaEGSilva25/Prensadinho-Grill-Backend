package com.unasp.Prensadinho.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private Long productCode;

    @Column(nullable = false)
    @Size(min = 3, max = 150)
    @NotNull
    private String name;

    @Column(nullable = false)
    @Positive
    @NotNull
    private BigDecimal unitPrice;

    @Column(nullable = false)
    @PositiveOrZero
    @NotNull
    private int quantity;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Product(){}
    public Product(Long productCode, String name, BigDecimal unitPrice, int quantity, LocalDateTime createdAt) {
        this.productCode = productCode;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.createdAt = createdAt;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull Long getProductCode() {
        return productCode;
    }

    public void setProductCode(@NotNull Long productCode) {
        this.productCode = productCode;
    }

    public @Size(min = 3, max = 150) @NotNull String getName() {
        return name;
    }

    public void setName(@Size(min = 3, max = 150) @NotNull String name) {
        this.name = name;
    }

    public @Positive @NotNull BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(@Positive @NotNull BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @PositiveOrZero
    @NotNull
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(@PositiveOrZero @NotNull int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
