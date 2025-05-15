package com.unasp.Prensadinho.controller;

import com.unasp.Prensadinho.DTO.spunDTO.SpunDTO;
import com.unasp.Prensadinho.domain.Spun;
import com.unasp.Prensadinho.service.SpunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spuns")
public class SpunController {

    @Autowired
    private SpunService service;

    @GetMapping
    public ResponseEntity<List<Spun>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Spun> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SpunDTO> createSpun(@RequestBody SpunDTO dto) {
        service.createSpun(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSpun(@PathVariable Long id, @RequestBody SpunDTO dto){
        service.updateSpun(dto, id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpun(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
