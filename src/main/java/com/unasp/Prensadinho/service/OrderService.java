package com.unasp.Prensadinho.service;

import com.unasp.Prensadinho.DTO.orderDTO.OrderDTO;
import com.unasp.Prensadinho.DTO.orderItemDTO.OrderItemDTO;
import com.unasp.Prensadinho.domain.Order;
import com.unasp.Prensadinho.domain.OrderItem;
import com.unasp.Prensadinho.domain.Product;
import com.unasp.Prensadinho.domain.Spun;
import com.unasp.Prensadinho.exceptions.InvalidStockRangeException;
import com.unasp.Prensadinho.exceptions.NotFoundException;
import com.unasp.Prensadinho.repository.OrderRepository;
import com.unasp.Prensadinho.repository.ProductRepository;
import com.unasp.Prensadinho.repository.SpunRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SpunRepository spunRepository;

    public List<Order> findAll(){
        return repository.findAll();
    }

    public Order findById(Long id){
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void createOrder(OrderDTO orderDTO) {
        Spun spun = new Spun();
        spun.setPhone(orderDTO.spun().getPhone());
        spun.setName(orderDTO.spun().getName());

        Order ord = new Order();
        ord.setPaymentType(orderDTO.type());
        ord.setSpun(spun);

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalValue = BigDecimal.ZERO;

        for (OrderItemDTO itemDTO : orderDTO.items()) {
            Product product = productRepository.findByProductCode(itemDTO.productCode())
                    .orElseThrow(NotFoundException::new);

            if (product.getQuantity() < itemDTO.quantity()) {
                throw new InvalidStockRangeException("Quantidade insuficiente no estoque para o produto: " + product.getName());
            }

            product.setQuantity(product.getQuantity() - itemDTO.quantity());
            productRepository.save(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.quantity());
            orderItem.setOrder(ord);

            orderItems.add(orderItem);

            totalValue = totalValue.add(product.getUnitPrice().multiply(BigDecimal.valueOf(itemDTO.quantity())));
        }

        ord.setItems(orderItems);
        ord.setValue(totalValue);

        spun.getOrders().add(ord);

        repository.save(ord);
        spunRepository.save(spun);
    }

    @Transactional
    public void updateOrder(Long orderId, OrderDTO orderDTO) {
        Order existingOrder = repository.findById(orderId)
                .orElseThrow(NotFoundException::new);

        Spun spun = existingOrder.getSpun();
        spun.setPhone(orderDTO.spun().getPhone());
        spun.setName(orderDTO.spun().getName());

        for (OrderItem oldItem : existingOrder.getItems()) {
            Product product = oldItem.getProduct();
            product.setQuantity(product.getQuantity() + oldItem.getQuantity());
            productRepository.save(product);
        }

        existingOrder.getItems().clear();

        List<OrderItem> updatedItems = new ArrayList<>();
        BigDecimal updatedTotal = BigDecimal.ZERO;

        for (OrderItemDTO itemDTO : orderDTO.items()) {
            Product product = productRepository.findByProductCode(itemDTO.productCode())
                    .orElseThrow(NotFoundException::new);

            if (product.getQuantity() < itemDTO.quantity()) {
                throw new InvalidStockRangeException("Quantidade insuficiente no estoque para o produto: " + product.getName());
            }

            product.setQuantity(product.getQuantity() - itemDTO.quantity());
            productRepository.save(product);

            OrderItem newItem = new OrderItem();
            newItem.setProduct(product);
            newItem.setQuantity(itemDTO.quantity());
            newItem.setOrder(existingOrder);

            updatedItems.add(newItem);

            updatedTotal = updatedTotal.add(product.getUnitPrice().multiply(BigDecimal.valueOf(itemDTO.quantity())));
        }

        existingOrder.setItems(updatedItems);
        existingOrder.setValue(updatedTotal);
        existingOrder.setPaymentType(orderDTO.type());

        repository.save(existingOrder);
        spunRepository.save(spun);
    }

    @Transactional
    public void deleteOrder(Long id){
        repository.deleteById(id);
    }
}
