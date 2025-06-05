package com.unasp.Prensadinho.service;

import com.unasp.Prensadinho.DTO.spunDTO.SpunDTO;
import com.unasp.Prensadinho.domain.Spun;
import com.unasp.Prensadinho.exceptions.NotFoundException;
import com.unasp.Prensadinho.repository.SpunRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpunService {

    @Autowired
    private SpunRepository repository;

    public List<Spun> findAll(){
        return repository.findAll();
    }
    public Spun findById(Long id){
        Spun spun = repository.findById(id).orElseThrow(NotFoundException::new);
        spun.getOrders().size();
        return spun;
    }

    @Transactional
    public void createSpun(SpunDTO spun){
        Spun sp = new Spun();
        sp.setName(spun.name());
        sp.setPhone(spun.phone());
        repository.save(sp);
    }

    @Transactional
    public void updateSpun(SpunDTO spun, Long id){
        Spun sp = findById(id);
        sp.setName(spun.name());
        sp.setPhone(spun.phone());
        repository.save(sp);
    }
    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }
}
