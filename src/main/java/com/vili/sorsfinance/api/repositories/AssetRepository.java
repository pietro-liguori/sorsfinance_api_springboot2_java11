package com.vili.sorsfinance.api.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {

	List<Asset> findByNameIgnoreCase(String name);
	Page<Asset> findByType(Integer type, Pageable pageable);
	Page<Asset> findByCategoriesId(Long category, Pageable pageable);
	Page<Asset> findByCategoriesIdAndType(Long category, Integer type, Pageable pageable);
}
