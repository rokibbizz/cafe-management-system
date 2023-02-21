package com.rokib.cafe.application.repository;

import com.rokib.cafe.domain.dto.user.UserDTO;
import com.rokib.cafe.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Integer> {
    Optional<Object> findByEmail(String username);
    List<UserDTO> gellAllUser();

    List<String> gellAllAdmin();
}
