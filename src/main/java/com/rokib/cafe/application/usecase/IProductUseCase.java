package com.rokib.cafe.application.usecase;

import com.rokib.cafe.domain.entities.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IProductUseCase {
    ResponseEntity<List<Product>> productListGet();

    ResponseEntity<String> productCreate(Map<String, String> requestMap);

    ResponseEntity<String> productUpdate(Map<String, String> requestMap);
}
