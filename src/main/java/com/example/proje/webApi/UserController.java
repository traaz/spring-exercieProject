package com.example.proje.webApi;

import com.example.proje.core.results.DataResult;
import com.example.proje.core.results.Result;
import com.example.proje.entity.User;
import com.example.proje.services.UserManager;
import com.example.proje.services.requests.CreateUserRequest;
import com.example.proje.services.responses.GetAllUsersResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserManager userManager;

    @PostMapping("/add")
    public Result createUser(@RequestBody CreateUserRequest createUserRequest) {
        return userManager.createUser(createUserRequest);
    }
    @PostMapping("/login")
    public Result login(@RequestBody CreateUserRequest createUserRequest) {
        return userManager.login(createUserRequest);
    }
    @GetMapping("/getall")
    public DataResult<List<GetAllUsersResponse>> getAllUser() {
        return userManager.getAllUser();
    }
}
