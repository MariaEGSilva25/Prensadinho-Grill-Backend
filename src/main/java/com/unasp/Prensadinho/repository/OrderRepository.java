package com.unasp.Prensadinho.repository;

import com.unasp.Prensadinho.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
