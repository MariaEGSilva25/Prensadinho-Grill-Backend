package com.unasp.Prensadinho.service;

import com.unasp.Prensadinho.DTO.orderDTO.OrderDTO;
import com.unasp.Prensadinho.DTO.orderItemDTO.OrderItemDTO;
import com.unasp.Prensadinho.domain.Order;
import com.unasp.Prensadinho.domain.OrderItem;
import com.unasp.Prensadinho.domain.Product;
import com.unasp.Prensadinho.domain.Spun;
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
        return repository.findById(id).orElseThrow();
    }

    @Transactional
    public void createOrder(OrderDTO orderDTO) {
        Spun spun = spunRepository.findById(orderDTO.spun().getId())
                .orElseThrow(() -> new IllegalArgumentException("Spun não encontrado"));

        Order ord = new Order();
        ord.setPaymentType(orderDTO.type());
        ord.setSpun(spun); // usa o spun carregado do banco, não o do DTO

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalValue = BigDecimal.ZERO;

        for (OrderItemDTO itemDTO : orderDTO.items()) {
            Product product = productRepository.findByProductCode(itemDTO.productCode())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + itemDTO.productCode()));

            if (product.getQuantity() < itemDTO.quantity()) {
                throw new IllegalArgumentException("Quantidade insuficiente no estoque para o produto: " + product.getName());
            }

            // Atualiza estoque
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

        // Adiciona a order na lista do spun
        spun.getOrders().add(ord);

        // Salva a order
        repository.save(ord);

        // Atualiza o spun com a nova order
        spunRepository.save(spun);
    }

//
//    @Transactional
//    public void updateOrder(Long id, OrderDTO order){
//        Order ord = new Order();
//        List<Product> products = productRepository.findByProductCodeIn(order.productsCode());
//        ord.setProducts(products);
//        ord.setPaymentType(order.type());
//        ord.setValue(calcTotalValue(products));
//        ord.setSpun(order.spun());
//
//        repository.save(ord);
//    }

    @Transactional
    public void deleteOrder(Long id){
        repository.deleteById(id);
    }


    public BigDecimal calcTotalValue(List<Product> products) {
        BigDecimal total = BigDecimal.ZERO;
        for (Product product : products) {
            BigDecimal itemTotal = product.getUnitPrice().multiply(BigDecimal.valueOf(product.getQuantity()));
            total = total.add(itemTotal);
        }
        return total;
    }

}
