package com.rokib.cafe.application.usecase.impl;

import com.rokib.cafe.application.repository.IProductRepository;
import com.rokib.cafe.application.usecase.IProductUseCase;
import com.rokib.cafe.domain.entities.Category;
import com.rokib.cafe.domain.entities.Product;
import com.rokib.cafe.domain.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductUseCase implements IProductUseCase {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public ResponseEntity<List<Product>> productListGet() {
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> productCreate(Map<String, String> requestMap) {
        Product product = Product.builder().name(requestMap.get("name")).description(requestMap.get("description")).status(requestMap.get("status")).price(Integer.parseInt(requestMap.get("price"))).category(Category.builder().id(Integer.parseInt(requestMap.get("categoryId"))).build()).build();
        productRepository.save(product);
        return new ResponseEntity<>("Product Saved Successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> productUpdate(Map<String, String> requestMap) {

        Optional<Product> optionalProduct = productRepository.findById(Integer.parseInt(requestMap.get("id")));
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (!Util.isNullOrWhiteSpace(requestMap.get("name"))) {
                product.setName(requestMap.get("name"));
            }
            if (!Util.isNullOrWhiteSpace(requestMap.get("description"))) {
                product.setDescription(requestMap.get("description"));
            }
            if (!Util.isNullOrWhiteSpace(requestMap.get("status"))) {
                product.setStatus(requestMap.get("status"));
            }
            if (!Util.isNullOrWhiteSpace(requestMap.get("price"))) {
                product.setPrice(Integer.parseInt(requestMap.get("price")));
            }
            if (!Util.isNullOrWhiteSpace(requestMap.get("categoryId"))) {
                product.setCategory(Category.builder().id(Integer.parseInt(requestMap.get("categoryId"))).build());
            }
            productRepository.save(product);
            return new ResponseEntity<>("Product Saved Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product Not Found", HttpStatus.NO_CONTENT);
        }
    }
}
