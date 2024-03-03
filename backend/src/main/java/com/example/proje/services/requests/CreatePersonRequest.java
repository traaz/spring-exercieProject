package com.example.proje.services.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePersonRequest {
    private String name;
    private String surname;
    private int age;
    private int countryId;
    private int stateId;
}
