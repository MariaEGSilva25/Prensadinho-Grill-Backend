package com.unasp.Prensadinho.DTO.orderDTO;

import com.unasp.Prensadinho.DTO.orderItemDTO.OrderItemDTO;
import com.unasp.Prensadinho.domain.ENUM.PaymentType;
import com.unasp.Prensadinho.domain.Spun;

import java.util.List;

public record OrderDTO(List<OrderItemDTO> items, PaymentType type, Spun spun) {
}
