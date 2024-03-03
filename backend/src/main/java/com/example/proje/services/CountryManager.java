package com.example.proje.services;

import com.example.proje.core.mappers.ModelMapperService;
import com.example.proje.core.results.DataResult;
import com.example.proje.core.results.Result;
import com.example.proje.core.results.SuccessDataResult;
import com.example.proje.entity.Country;
import com.example.proje.entity.Person;
import com.example.proje.repository.CountryRepository;
import com.example.proje.services.requests.CreateCountryRequest;
import com.example.proje.services.responses.GetAllCountriesResponse;
import com.example.proje.services.responses.GetAllPeopleResponse;
import com.example.proje.services.rules.CountryBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CountryManager implements CountryService{
    private CountryRepository countryRepository;
    private ModelMapperService modelMapperService;
    private CountryBusinessRules countryBusinessRules;
    @Override
    public DataResult<List<GetAllCountriesResponse>> getAll() {
        List<Country> countries = countryRepository.findAll();
        List<GetAllCountriesResponse> responses = countries.stream()
                .map(country->this.modelMapperService.forResponse()
                        .map(country, GetAllCountriesResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<GetAllCountriesResponse>>(responses, "Data listelendi");

    }

    @Override
    public Result add(CreateCountryRequest createCountryRequest) {
        this.countryBusinessRules.checkIfCountryNameExists(createCountryRequest.getName());
        Country country = this.modelMapperService.forRequest().map(createCountryRequest, Country.class);
        countryRepository.save(country);
        System.out.println(country.getName());
            return new Result(true, "Eklendi");

    }
}
