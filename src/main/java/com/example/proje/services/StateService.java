package com.example.proje.services;

import com.example.proje.core.results.DataResult;
import com.example.proje.core.results.Result;
import com.example.proje.services.requests.CreateStateRequest;
import com.example.proje.services.responses.GetAllStatesResponse;
import com.example.proje.services.responses.GetStatesAccordingToCountry;

import java.util.List;

public interface StateService {
    DataResult<List<GetAllStatesResponse>> getAll();
    Result add(CreateStateRequest createStateRequest);
    DataResult<List<GetStatesAccordingToCountry>> getStatesAccordingToCountry(int id);
}
