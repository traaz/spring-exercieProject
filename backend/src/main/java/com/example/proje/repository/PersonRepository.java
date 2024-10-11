package com.example.proje.repository;

import com.example.proje.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByCountryId(int id);
    List<Person> findByStateId(int id);
    List<Person> findAllByOrderByNameAscSurnameAsc();
    
}
