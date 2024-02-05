package com.example.proje.webApi;

import com.example.proje.core.results.DataResult;
import com.example.proje.core.results.Result;
import com.example.proje.services.CountryService;
import com.example.proje.services.requests.CreateCountryRequest;
import com.example.proje.services.responses.GetAllCountriesResponse;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CountryControllerTest {
    CountryController countryController;
    CountryService countryService;

    @BeforeEach
    void setUp() {
        countryService = Mockito.mock(CountryService.class);
        countryController = new CountryController(countryService);
    }

    @Test
    @Order(1)
    void getAllCountries() {
        GetAllCountriesResponse response1 = new GetAllCountriesResponse(1,"Türkiye");
        GetAllCountriesResponse response2 = new GetAllCountriesResponse(2,"İtalya");
        GetAllCountriesResponse response3 = new GetAllCountriesResponse(3,"Almanya");
        List<GetAllCountriesResponse> responses = List.of(response1, response2, response3);
        DataResult<List<GetAllCountriesResponse>> dataResponse = new DataResult<List<GetAllCountriesResponse>>(responses,true,"Data Listelendi");

        when(countryService.getAll()).thenReturn(dataResponse);

        DataResult<List<GetAllCountriesResponse>> actual = countryController.getAll();
        Mockito.verify(countryService).getAll();

        assertEquals(dataResponse, actual);
        System.out.println(actual.getData());
        System.out.println(dataResponse.getData());
    }

    @Test
    @Order(2)
    void addCountry() {
        CreateCountryRequest request = new CreateCountryRequest("Portekiz");
        Result response = new Result(true,"Eklendi");

        when(countryService.add(request)).thenReturn(response);

        Result actual = countryController.Add(request);

        assertEquals(response, actual);

        System.out.println(actual);
        System.out.println(response);

    }
}