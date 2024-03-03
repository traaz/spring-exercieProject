package com.example.proje.services.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllPeopleResponse {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String countryName;
    private String stateName;
}
