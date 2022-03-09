package com.vili.sorsfinance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
