package com.rokib.cafe.application.usecase;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface IUserUseCase {

    ResponseEntity<String> signUp(Map<String, String> requestMap);
}
