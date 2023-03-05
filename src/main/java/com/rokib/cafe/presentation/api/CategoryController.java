package com.rokib.cafe.presentation.api;

import com.rokib.cafe.application.usecase.ICategoryUseCase;
import com.rokib.cafe.domain.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private ICategoryUseCase categoryUseCase;

    @GetMapping
    public ResponseEntity<List<Category>> CategoryListGet(){
        return categoryUseCase.CategoryListGet();
    }
    @PostMapping
    public ResponseEntity<String> CategoryCreate(@RequestBody Map<String,String> requestMap){
        return categoryUseCase.CategoryCreate(requestMap);
    }
    @PutMapping
    public ResponseEntity<String> CategoryUpdate(@RequestBody Map<String,String> requestMap){
        return categoryUseCase.CategoryUpdate(requestMap);
    }
    @DeleteMapping
    public ResponseEntity<String> CategoryDelete(@RequestBody Map<String,String> requestMap){
        return categoryUseCase.CategoryDelete(requestMap);
    }
}
