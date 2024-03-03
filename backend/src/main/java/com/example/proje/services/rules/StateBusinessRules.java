package com.example.proje.services.rules;

import com.example.proje.core.exceptions.DataIntegrityViolationException;
import com.example.proje.repository.StateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StateBusinessRules {
    private StateRepository stateRepository;
    public void checkIfStateNameExists(String name){
        if(stateRepository.existsByName(name)){
            throw new RuntimeException("Şehir Mevcut");
        }
    }

    public void checkIfStateIdExists(int id){
        if(!stateRepository.existsById(id)){
            throw new DataIntegrityViolationException("Girilen şehir id'si ile eşleşme yok.");

        }
    }

}
