package com.rokib.cafe.application.usecase;

import com.rokib.cafe.domain.dto.auth.AuthenticationRequest;
import com.rokib.cafe.domain.dto.auth.AuthenticationResponse;
import com.rokib.cafe.domain.dto.auth.RegisterRequest;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IAuthenticationUseCase {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    ResponseEntity<String> changePassword(Map<String, String> requestMap);

    ResponseEntity<String> forgotPassword(Map<String, String> requestMap);
}
