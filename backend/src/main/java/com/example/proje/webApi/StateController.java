package com.example.proje.webApi;


import com.example.proje.core.results.DataResult;
import com.example.proje.core.results.Result;
import com.example.proje.entity.Country;
import com.example.proje.entity.State;
import com.example.proje.services.StateService;
import com.example.proje.services.requests.CreateCountryRequest;
import com.example.proje.services.requests.CreateStateRequest;
import com.example.proje.services.responses.GetAllStatesResponse;
import com.example.proje.services.responses.GetStatesAccordingToCountry;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/state")
@AllArgsConstructor
@CrossOrigin("*")
public class StateController {
    private StateService stateService;

    @GetMapping("/getAll")
    public DataResult<List<GetAllStatesResponse>> getAll(){
        return stateService.getAll();
    }

    @PostMapping("/add")
    public Result Add(@RequestBody  CreateStateRequest createStateRequest){
        return stateService.add(createStateRequest);
    }
    @GetMapping("/country/{countryId}")
    public DataResult<List<GetStatesAccordingToCountry>> getStatesAccordingToCountries(@PathVariable int countryId){
        return stateService.getStatesAccordingToCountry(countryId);
    }

}
