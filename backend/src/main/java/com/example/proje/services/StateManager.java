package com.example.proje.services;

import com.example.proje.core.mappers.ModelMapperService;
import com.example.proje.core.results.DataResult;
import com.example.proje.core.results.Result;
import com.example.proje.core.results.SuccessDataResult;
import com.example.proje.entity.Country;
import com.example.proje.entity.State;
import com.example.proje.repository.StateRepository;
import com.example.proje.services.requests.CreateStateRequest;
import com.example.proje.services.responses.GetAllCountriesResponse;
import com.example.proje.services.responses.GetAllStatesResponse;
import com.example.proje.services.responses.GetStatesAccordingToCountry;
import com.example.proje.services.rules.CountryBusinessRules;
import com.example.proje.services.rules.StateBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StateManager implements StateService {
    private StateRepository stateRepository;
    private ModelMapperService modelMapperService;
    private StateBusinessRules stateBusinessRules;
    private CountryBusinessRules countryBusinessRules;
    @Override
    public DataResult<List<GetAllStatesResponse>> getAll() {

        List<State> states = stateRepository.findAll();
        List<GetAllStatesResponse> responses = states.stream()
                .map(state->this.modelMapperService.forResponse()
                        .map(state, GetAllStatesResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<GetAllStatesResponse>>(responses, "Data listelendi");
    }

    @Override
    public Result add(CreateStateRequest createStateRequest) {
        this.stateBusinessRules.checkIfStateNameExists(createStateRequest.getName());
        this.countryBusinessRules.checkIfCountryIdExists(createStateRequest.getCountryId());
        State state = this.modelMapperService.forRequest().map(createStateRequest, State.class);
        stateRepository.save(state);
        return new Result(true,"Eklendi");
    }

    @Override
    public DataResult<List<GetStatesAccordingToCountry>> getStatesAccordingToCountry(int id) {
        List<State> states = stateRepository.findByCountryId(id);
        List<GetStatesAccordingToCountry> responses = states.stream()
                .map(state ->this.modelMapperService.forResponse()
                        .map(state, GetStatesAccordingToCountry.class)
                ).collect(Collectors.toList());
        return new SuccessDataResult<List<GetStatesAccordingToCountry>>(responses,"Data Listelendi");
    }
}
