package com.example.proje.webApi;

import com.example.proje.core.results.DataResult;
import com.example.proje.core.results.Result;
import com.example.proje.entity.Person;
import com.example.proje.services.PersonManager;
import com.example.proje.services.PersonService;
import com.example.proje.services.requests.CreatePersonRequest;
import com.example.proje.services.responses.GetAllPeopleResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {
    private PersonService   personService;

    @GetMapping("/getAll")
    public DataResult<List<GetAllPeopleResponse>> getAll(){
        return personService.getAll();
    }
    @PostMapping("/add")
    public Result add(@RequestBody CreatePersonRequest createPersonRequest){
        return personService.Add(createPersonRequest);
    }
}
