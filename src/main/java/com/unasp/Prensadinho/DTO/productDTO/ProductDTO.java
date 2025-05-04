package com.unasp.Prensadinho.DTO.productDTO;

import java.math.BigDecimal;

public record ProductDTO(Long productCode, String name, BigDecimal unitPrice,
                         int quantity, int minimumStock, int maximumStock) {
}
