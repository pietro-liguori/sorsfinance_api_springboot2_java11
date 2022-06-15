package com.vili.sorsfinance.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	Page<Address> findByCityId(Long city, Pageable pageable);
	Page<Address> findByCityStateId(Long state, Pageable pageable);
	Page<Address> findByCityStateCountryId(Long country, Pageable pageable);
	Page<Address> findByContactsOwnerId(Long owner, Pageable pageable);
	Page<Address> findByPreferred(Boolean preferred, Pageable pageable);
	Page<Address> findByCityIdAndPreferred(Long city, Boolean preferred, Pageable pageable);
	Page<Address> findByCityStateIdAndPreferred(Long state, Boolean preferred, Pageable pageable);
	Page<Address> findByCityStateCountryIdAndPreferred(Long country, Boolean preferred, Pageable pageable);
	Page<Address> findByContactsOwnerIdAndPreferred(Long owner, Boolean preferred, Pageable pageable);
	Page<Address> findByContactsOwnerIdAndCityId(Long owner, Long city, Pageable pageable);
	Page<Address> findByContactsOwnerIdAndCityStateId(Long owner, Long state, Pageable pageable);
	Page<Address> findByContactsOwnerIdAndCityStateCountryId(Long owner, Long country, Pageable pageable);
	Page<Address> findByContactsOwnerIdAndCityIdAndPreferred(Long owner, Long city, Boolean preferred, Pageable pageable);
	Page<Address> findByContactsOwnerIdAndCityStateIdAndPreferred(Long owner, Long state, Boolean preferred, Pageable pageable);
	Page<Address> findByContactsOwnerIdAndCityStateCountryIdAndPreferred(Long owner, Long country, Boolean preferred, Pageable pageable);
}
