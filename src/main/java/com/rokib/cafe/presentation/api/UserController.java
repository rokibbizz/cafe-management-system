package com.rokib.cafe.presentation.api;

import com.rokib.cafe.application.usecase.IUserUseCase;
import com.rokib.cafe.domain.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserUseCase userUseCase;

    @GetMapping("/hello")
    public String hello(){
        return "from auth";
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> gellAllUser(){
        return userUseCase.gellAllUser();
    }

    @PostMapping("/activateuser")
    public ResponseEntity<String> activateUser(@RequestBody(required = true) Map<String,String> requestMap){
        return userUseCase.activateUser(requestMap);
    }

}
