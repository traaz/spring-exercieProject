package com.example.proje.services;

import com.example.proje.core.results.DataResult;
import com.example.proje.core.results.Result;
import com.example.proje.entity.Country;
import com.example.proje.entity.Person;
import com.example.proje.services.requests.CreateCountryRequest;
import com.example.proje.services.responses.GetAllCountriesResponse;

import java.util.List;

public interface CountryService {
    DataResult<List<GetAllCountriesResponse>> getAll();
    Result add(CreateCountryRequest createCountryRequest);

}
