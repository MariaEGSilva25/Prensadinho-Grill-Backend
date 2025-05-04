package com.unasp.Prensadinho.repository;

import com.unasp.Prensadinho.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProductCode(Long id);

}
