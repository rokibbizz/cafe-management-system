package com.rokib.cafe.application.usecase.impl;

import com.rokib.cafe.application.repository.ICategoryRepository;
import com.rokib.cafe.application.usecase.ICategoryUseCase;
import com.rokib.cafe.domain.entities.Category;
import com.rokib.cafe.presentation.security.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryUseCase implements ICategoryUseCase {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private AuthenticationFilter authenticationFilter;


    @Override
    public ResponseEntity<List<Category>> CategoryListGet() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> CategoryCreate(Map<String, String> requestMap) {
        if (authenticationFilter.isUser()) {
            return new ResponseEntity<>("Illegal Access, don't try", HttpStatus.BAD_REQUEST);
        }
        Category category = Category.builder().name(requestMap.get("name")).build();
        categoryRepository.save(category);
        return new ResponseEntity<>("Category Saved Successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> CategoryUpdate(Map<String, String> requestMap) {
        if (authenticationFilter.isUser()) {
            return new ResponseEntity<>("Illegal Access", HttpStatus.BAD_REQUEST);
        }

        Optional<Category> dbCategory = categoryRepository.findById(Integer.parseInt(requestMap.get("id")));
        if (dbCategory.isPresent()) {
            Category category = dbCategory.get();
            category.setName(requestMap.get("name"));
            categoryRepository.save(category);
            return new ResponseEntity<>("Category Saved Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Category Not Found", HttpStatus.NO_CONTENT);
        }

    }

    @Override
    public ResponseEntity<String> CategoryDelete(Map<String, String> requestMap) {
        return null;
    }


}
