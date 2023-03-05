package com.rokib.cafe.application.repository;

import com.rokib.cafe.domain.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBillRepository extends JpaRepository<Bill,Integer> {
}
