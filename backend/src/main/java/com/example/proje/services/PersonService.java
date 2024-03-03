package com.example.proje.services;

import com.example.proje.core.results.DataResult;
import com.example.proje.core.results.Result;
import com.example.proje.entity.Person;
import com.example.proje.services.requests.CreatePersonRequest;
import com.example.proje.services.responses.GetAllPeopleResponse;

import java.util.List;

public interface PersonService {
    DataResult<List<GetAllPeopleResponse>> getAll();
    Result Add(CreatePersonRequest createPersonRequest);
}
