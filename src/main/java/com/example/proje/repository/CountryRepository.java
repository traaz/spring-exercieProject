package com.example.proje.repository;

import com.example.proje.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    boolean existsByName(String name);
    boolean existsById(int id);
}
