package com.vili.sorsfinance.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByNameIgnoreCase(String name);
}
