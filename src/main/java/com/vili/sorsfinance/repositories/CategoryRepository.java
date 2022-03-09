package com.vili.sorsfinance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
