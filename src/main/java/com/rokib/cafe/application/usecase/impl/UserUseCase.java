package com.rokib.cafe.application.usecase.impl;

import com.rokib.cafe.application.usecase.IUserUseCase;
import org.springframework.http.ResponseEntity;

import java.util.Map;


public class UserUseCase implements IUserUseCase {
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        return null;
    }
}
