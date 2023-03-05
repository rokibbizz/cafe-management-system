package com.rokib.cafe.application.usecase.impl;

import com.rokib.cafe.application.repository.IUserRepository;
import com.rokib.cafe.application.usecase.IUserUseCase;
import com.rokib.cafe.domain.AllEnums;
import com.rokib.cafe.domain.dto.user.UserDTO;
import com.rokib.cafe.domain.entities.User;
import com.rokib.cafe.domain.utils.Util;
import com.rokib.cafe.presentation.security.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Component
public class UserUseCase implements IUserUseCase {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Autowired
    private Util util;

    @Override
    public ResponseEntity<List<UserDTO>> gellAllUser() {
        if (authenticationFilter.isUser()) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            List<UserDTO> allUser = userRepository.gellAllUser();
            return new ResponseEntity<>(allUser, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> activateUser(Map<String, String> requestMap) {
        Optional<User> user = userRepository.findById(Integer.parseInt(requestMap.get("id")));
        if(authenticationFilter.isUser()){
            return new ResponseEntity<>("Illegal Access", HttpStatus.BAD_REQUEST);
        }
        if (user.isPresent()) {
            User dbUser = user.get();
            dbUser.setStatus("active");
            userRepository.save(dbUser);
            util.sendEmail("Status Changed To Active", AllEnums.EmailReceiver.ALLADMIN,new Object());
            return new ResponseEntity<>("User Activated Successfully", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("User Not Found", HttpStatus.NO_CONTENT);
        }
    }
}
