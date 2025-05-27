package com.unasp.Prensadinho.controller;

import com.unasp.Prensadinho.DTO.productDTO.ProductDTO;
import com.unasp.Prensadinho.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }
    @GetMapping("/code/{productCode}")
    public ResponseEntity<ProductDTO> findByProductCode(@PathVariable Long productCode){
        return ResponseEntity.ok(productService.findByProductCode(productCode));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO product){
        productService.createProduct(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.productCode())
                .toUri();
        return ResponseEntity.created(uri).body(product);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id ,@RequestBody ProductDTO dto){
        productService.updateProduct(dto);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{productCode}")
    public ResponseEntity<Void> deleteProductCode(@PathVariable Long productCode){
        productService.deleteProductCode(productCode);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAllProducts() {
        productService.deleteAllProducts(); // correto: chama o service
        return ResponseEntity.ok("Todos os produtos foram deletados com sucesso.");
    }
}
