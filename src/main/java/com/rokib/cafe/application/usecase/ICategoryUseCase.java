package com.rokib.cafe.application.usecase;

import com.rokib.cafe.domain.entities.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ICategoryUseCase {

    ResponseEntity<List<Category>> CategoryListGet();

    ResponseEntity<String> CategoryCreate(Map<String, String> requestMap);

    ResponseEntity<String> CategoryUpdate(Map<String, String> requestMap);

    ResponseEntity<String> CategoryDelete(Map<String, String> requestMap);
}
