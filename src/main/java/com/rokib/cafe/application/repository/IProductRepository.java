package com.rokib.cafe.application.repository;

import com.rokib.cafe.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Integer> {
}
