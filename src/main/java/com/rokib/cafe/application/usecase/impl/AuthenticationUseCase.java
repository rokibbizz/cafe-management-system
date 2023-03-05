package com.rokib.cafe.application.usecase.impl;

import com.rokib.cafe.application.repository.IUserRepository;
import com.rokib.cafe.application.usecase.IAuthenticationUseCase;
import com.rokib.cafe.domain.AllEnums;
import com.rokib.cafe.domain.dto.auth.AuthenticationRequest;
import com.rokib.cafe.domain.dto.auth.AuthenticationResponse;
import com.rokib.cafe.domain.dto.auth.RegisterRequest;
import com.rokib.cafe.domain.entities.User;
import com.rokib.cafe.presentation.security.AuthenticationFilter;
import com.rokib.cafe.presentation.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationUseCase implements IAuthenticationUseCase {
    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    private final AuthenticationFilter authenticationFilter;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {

        Optional<Object> user1 = repository.findByEmail(request.getEmail());

        if (user1.isPresent()) {
            return AuthenticationResponse.builder()
                    .token("user already exist")
                    .build();
        }
        com.rokib.cafe.domain.entities.User user = com.rokib.cafe.domain.entities.User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(String.valueOf(AllEnums.Role.USER))
                .build();
        repository.save(user);
        var jwtToken = jwtUtils.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        com.rokib.cafe.domain.entities.User user = (User) repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtUtils.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        Optional<Object> user = repository.findByEmail(authenticationFilter.getCurrentUser());
        if (user.isPresent()) {
            User dbUser = (User) user.get();
            if(!dbUser.getPassword().equals(requestMap.get("oldPassword"))){
                return new ResponseEntity<String>("Old Password And Confirm Password Did Not Matched", HttpStatus.BAD_REQUEST);
            }
            dbUser.setPassword(passwordEncoder.encode(requestMap.get("newpPassword")));
            repository.save(dbUser);
            return new ResponseEntity<String>("Password Changed Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("User Not Found", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
        Optional<Object> user = repository.findByEmail(requestMap.get("email"));
        if (user.isPresent()) {
            User dbUser = (User) user.get();
            if(!dbUser.getPassword().equals(requestMap.get("oldPassword"))){
                return new ResponseEntity<String>("Old Password And Confirm Password Did Not Matched", HttpStatus.BAD_REQUEST);
            }
            dbUser.setPassword(passwordEncoder.encode(requestMap.get("newpPassword")));
            repository.save(dbUser);
            return new ResponseEntity<String>("Password Changed Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("User Not Found", HttpStatus.BAD_REQUEST);
        }
    }
}
