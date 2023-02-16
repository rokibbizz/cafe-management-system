package com.rokib.cafe.presentation.api;

import com.rokib.cafe.application.usecase.IUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserUseCase userUseCase;
    @PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody Map<String,String> requestMap){
       return userUseCase.signUp(requestMap);
    }
}
