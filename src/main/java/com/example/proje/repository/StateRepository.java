package com.example.proje.repository;

import com.example.proje.core.results.DataResult;
import com.example.proje.core.results.SuccessDataResult;
import com.example.proje.entity.State;
import com.example.proje.services.responses.GetStatesAccordingToCountry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Integer> {
    List<State> findByCountryId(int id);
    boolean existsByName(String name);
    boolean existsById(int id);
}
