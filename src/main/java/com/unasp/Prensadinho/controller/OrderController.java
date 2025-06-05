package com.unasp.Prensadinho.controller;

import com.unasp.Prensadinho.DTO.orderDTO.OrderDTO;
import com.unasp.Prensadinho.DTO.productDTO.ProductDTO;
import com.unasp.Prensadinho.domain.Order;
import com.unasp.Prensadinho.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody OrderDTO order) {
        service.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrder(@PathVariable Long id, @RequestBody OrderDTO order) {
        service.updateOrder(id, order);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        service.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
