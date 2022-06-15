package com.vili.sorsfinance.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
