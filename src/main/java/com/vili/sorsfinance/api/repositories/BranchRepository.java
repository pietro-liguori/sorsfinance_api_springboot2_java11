package com.vili.sorsfinance.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {

	List<Branch> findByNameIgnoreCase(String name);
}
