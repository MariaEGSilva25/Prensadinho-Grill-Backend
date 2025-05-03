package com.unasp.Prensadinho.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
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

    @Column(nullable = false)
    @PositiveOrZero
    @NotNull
    private int minimumStock;

    @Column(nullable = false)
    @PositiveOrZero
    @NotNull
    private int maximumStock;

    public Product(){}
    public Product(Long productCode, String name, BigDecimal unitPrice, int quantity, int minimumStock, int maximumStock) {
        this.productCode = productCode;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.minimumStock = minimumStock;
        this.maximumStock = maximumStock;
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

    @PositiveOrZero
    @NotNull
    public int getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(@PositiveOrZero @NotNull int minimumStock) {
        this.minimumStock = minimumStock;
    }

    @PositiveOrZero
    @NotNull
    public int getMaximumStock() {
        return maximumStock;
    }

    public void setMaximumStock(@PositiveOrZero @NotNull int maximumStock) {
        this.maximumStock = maximumStock;
    }
}
