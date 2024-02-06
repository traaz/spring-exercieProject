package com.example.proje.services;

import com.example.proje.core.mappers.ModelMapperService;
import com.example.proje.core.results.*;
import com.example.proje.entity.User;
import com.example.proje.repository.UserRepository;
import com.example.proje.security.JwtService;
import com.example.proje.security.UserDetailsServiceImpl;
import com.example.proje.services.requests.CreateUserRequest;
import com.example.proje.services.responses.GetAllUsersResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserManager {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapperService modelMapperService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;



    public Result createUser(CreateUserRequest createUserRequest){
        User checkUser = userRepository.findByName(createUserRequest.getName());
        if(checkUser != null){
            return new ErrorResult("Kullanici Mevcut");
        }
        createUserRequest.setPassword(encoder.encode(createUserRequest.getPassword()));
        User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
        userRepository.save(user);
        return new SuccessResult("Kullanici Eklendi");
    }


    public DataResult login(CreateUserRequest createUserRequest){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                createUserRequest.getName(), createUserRequest.getPassword()));

        if(authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);

            jwtService.generateToken(createUserRequest.getName());
            return new SuccessDataResult<>(jwtService.generateToken(createUserRequest.getName()), "Token olusturuldu");
        }else {
            return new ErrorDataResult("Giriş Hatalı");
        }



    }

    public DataResult<List<GetAllUsersResponse>> getAllUser(){
        List<User> users = userRepository.findAll();
        System.out.println(users);
        List<GetAllUsersResponse> responses = users.stream()
                .map(user->this.modelMapperService.forResponse()
                        .map(user, GetAllUsersResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<GetAllUsersResponse>>(responses, "Data listelendi");
    }
}
