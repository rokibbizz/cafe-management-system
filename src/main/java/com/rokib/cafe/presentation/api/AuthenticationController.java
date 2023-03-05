package com.rokib.cafe.presentation.api;


import com.rokib.cafe.application.usecase.IAuthenticationUseCase;
import com.rokib.cafe.domain.dto.auth.AuthenticationRequest;
import com.rokib.cafe.domain.dto.auth.AuthenticationResponse;
import com.rokib.cafe.domain.dto.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationUseCase service;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/checkToken")
    public ResponseEntity<String> checkToken(@RequestBody Map<String, String> requestMap) {
        return service.changePassword(requestMap);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody Map<String, String> requestMap) {
        return service.changePassword(requestMap);
    }
    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> requestMap) {
        return service.forgotPassword(requestMap);
    }


}
