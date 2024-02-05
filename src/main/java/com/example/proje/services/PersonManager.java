package com.example.proje.services;

import com.example.proje.core.mappers.ModelMapperService;
import com.example.proje.core.results.DataResult;
import com.example.proje.core.results.Result;
import com.example.proje.core.results.SuccessDataResult;
import com.example.proje.entity.Person;
import com.example.proje.repository.PersonRepository;
import com.example.proje.services.requests.CreatePersonRequest;
import com.example.proje.services.responses.GetAllPeopleResponse;
import com.example.proje.services.rules.CountryBusinessRules;
import com.example.proje.services.rules.StateBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonManager implements PersonService{
    private PersonRepository personRepository;
    private ModelMapperService modelMapperService;
    private CountryBusinessRules countryBusinessRules;
    private StateBusinessRules stateBusinessRules;

    @Override
    public DataResult<List<GetAllPeopleResponse>> getAll() {
        List<Person> personList = personRepository.findAll();
        List<GetAllPeopleResponse> responses = personList.stream()
                .map(person->this.modelMapperService.forResponse()
                        .map(person, GetAllPeopleResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<GetAllPeopleResponse>>(responses, "Data Listelendi");

    }

    @Override
    public Result Add(CreatePersonRequest createPersonRequest) {
        this.countryBusinessRules.checkIfCountryIdExists(createPersonRequest.getCountryId());
        this.stateBusinessRules.checkIfStateIdExists(createPersonRequest.getStateId());
        Person person = this.modelMapperService.forRequest().map(createPersonRequest, Person.class);
        personRepository.save(person);
        return new Result(true, "Eklendi");

    }
}
