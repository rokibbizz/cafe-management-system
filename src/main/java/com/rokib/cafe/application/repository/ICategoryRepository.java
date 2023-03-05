package com.rokib.cafe.application.repository;

import com.rokib.cafe.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category,Integer> {

}
