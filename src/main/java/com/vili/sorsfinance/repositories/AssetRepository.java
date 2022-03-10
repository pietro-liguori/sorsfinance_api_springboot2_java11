package com.vili.sorsfinance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.entities.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {

}
