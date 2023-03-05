package com.rokib.cafe.presentation.api;

import com.rokib.cafe.application.usecase.IProductUseCase;
import com.rokib.cafe.domain.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private IProductUseCase productUseCase;

    @GetMapping
    public ResponseEntity<List<Product>> productListGet() {
        return productUseCase.productListGet();
    }

    @PostMapping
    public ResponseEntity<String> productCreate(@RequestBody Map<String, String> requestMap) {
        return productUseCase.productCreate(requestMap);
    }

    @PutMapping
    public ResponseEntity<String> productUpdate(@RequestBody Map<String, String> requestMap) {
        return productUseCase.productUpdate(requestMap);
    }
}

