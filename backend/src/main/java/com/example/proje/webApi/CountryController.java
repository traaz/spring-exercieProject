package com.example.proje.webApi;

import com.example.proje.core.results.DataResult;
import com.example.proje.core.results.Result;
import com.example.proje.entity.Country;
import com.example.proje.entity.Person;
import com.example.proje.services.CountryService;
import com.example.proje.services.requests.CreateCountryRequest;
import com.example.proje.services.responses.GetAllCountriesResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
@AllArgsConstructor
@CrossOrigin("*")
public class CountryController {
    private CountryService countryService;

    @GetMapping("/getAll")
    public DataResult<List<GetAllCountriesResponse>> getAll(){
        return countryService.getAll();
    }
    @PostMapping("/add")
    public Result Add(@RequestBody  CreateCountryRequest createCountryRequest){
        return countryService.add(createCountryRequest);
    }

}
