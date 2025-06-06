package com.unasp.Prensadinho.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_spun")
public class Spun {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Size(min = 1, max = 170)
    private String name;

    @Column(nullable = false)
    private String phone;

    @JsonFormat(pattern="HH:mm yyyy-MM-dd")
    private LocalDateTime now = LocalDateTime.now();

    @OneToMany(mappedBy = "spun")
    @JsonManagedReference
    private List<Order> orders = new ArrayList<>();

    public Spun(){
    }

    public Spun(Long id, String name, String phone, LocalDateTime now, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.now = now;
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Size(min = 2, max = 170) String getName() {
        return name;
    }

    public void setName(@Size(min = 2, max = 170) String name) {
        this.name = name;
    }

    public @Size(min = 8, max = 11) String getPhone() {
        return phone;
    }

    public void setPhone(@Size(min = 8, max = 11) String phone) {
        this.phone = phone;
    }

    public LocalDateTime getNow() {
        return now;
    }

    public void setNow(LocalDateTime now) {
        this.now = now;
    }
}
