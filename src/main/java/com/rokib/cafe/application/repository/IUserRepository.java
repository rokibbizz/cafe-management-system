package com.rokib.cafe.application.repository;

import com.rokib.cafe.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Integer> {
}
