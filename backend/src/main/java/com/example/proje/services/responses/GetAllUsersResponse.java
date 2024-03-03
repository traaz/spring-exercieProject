package com.example.proje.services.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllUsersResponse {

    private int id;
    private String name;
    private String password;

}
