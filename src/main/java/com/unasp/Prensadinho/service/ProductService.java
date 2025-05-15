package com.unasp.Prensadinho.service;

import com.unasp.Prensadinho.DTO.productDTO.ProductDTO;
import com.unasp.Prensadinho.domain.Product;
import com.unasp.Prensadinho.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<ProductDTO> findAll(){
        List<Product> product = repository.findAll();
        return product.stream().map(prod -> new ProductDTO(prod.getProductCode(),prod.getName(),prod.getUnitPrice(),
                prod.getQuantity(),prod.getMinimumStock(),prod.getMaximumStock())).toList();
    }

    public ProductDTO findById(Long id){
        Product product = repository.findById(id).orElseThrow();
        return new ProductDTO(product.getProductCode(),product.getName(),product.getUnitPrice(),
                product.getQuantity(),product.getMinimumStock(),product.getMaximumStock());
    }
    public ProductDTO findByProductCode(Long productCode){
        Optional<Product> optionalProduct = repository.findByProductCode(productCode);
        Product product = optionalProduct.orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + productCode));
        return new ProductDTO(product.getProductCode(),product.getName(),product.getUnitPrice(),
                product.getQuantity(),product.getMinimumStock(),product.getMaximumStock());
    }
    @Transactional
    public void createProduct(ProductDTO productDTO){
        if (productDTO.minimumStock() > productDTO.maximumStock()) {
            throw new IllegalArgumentException("O estoque máximo deve ser maior ou igual ao mínimo.");
        }

        Product product = new Product();
        product.setId(product.getId());
        product.setProductCode(productDTO.productCode());
        product.setName(productDTO.name());
        product.setUnitPrice(productDTO.unitPrice());
        product.setQuantity(productDTO.quantity());
        product.setMinimumStock(productDTO.minimumStock());
        product.setMaximumStock(productDTO.maximumStock());

        repository.save(product);
    }
    @Transactional
    public void updateProduct(ProductDTO dto){
        if (dto.minimumStock() > dto.maximumStock()) {
            throw new IllegalArgumentException("O estoque máximo deve ser maior ou igual ao mínimo.");
        }
        Optional<Product> optionalProduct = repository.findByProductCode(dto.productCode());
        Product p = optionalProduct.orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: "));
        p.setProductCode(dto.productCode());
        p.setName(dto.name());
        p.setUnitPrice(dto.unitPrice());
        p.setQuantity(dto.quantity());
        p.setMinimumStock(dto.minimumStock());
        p.setMaximumStock(dto.maximumStock());
        repository.save(p);
    }
    @Transactional
    public void delete(Long id){
        Product p = repository.findById(id).orElseThrow();
        repository.deleteById(id);
    }
    @Transactional
    public void deleteProductCode(Long productCode){
        Optional<Product> optionalProduct = repository.findByProductCode(productCode);
        Product product = optionalProduct.orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + productCode));
        repository.delete(product);
    }
}