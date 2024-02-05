package com.example.proje.services.rules;

import com.example.proje.core.exceptions.BusinessException;
import com.example.proje.core.exceptions.DataIntegrityViolationException;
import com.example.proje.core.results.ErrorResult;
import com.example.proje.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CountryBusinessRules {
    private CountryRepository countryRepository;
    public void checkIfCountryNameExists(String name){
        if(countryRepository.existsByName(name)){
            throw new BusinessException("Ülke Mevcut");

        }

    }
    public void checkIfCountryIdExists(int id){
        if(!countryRepository.existsById(id)){
            throw new DataIntegrityViolationException("Girilen ülke id'si ile eşleşme yok.");

        }
    }
}
