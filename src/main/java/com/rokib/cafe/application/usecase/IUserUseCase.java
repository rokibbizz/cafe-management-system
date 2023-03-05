package com.rokib.cafe.application.usecase;

import com.rokib.cafe.domain.dto.user.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IUserUseCase {
    ResponseEntity<List<UserDTO>> gellAllUser();

    ResponseEntity<String> activateUser(Map<String, String> requestMap);
}
