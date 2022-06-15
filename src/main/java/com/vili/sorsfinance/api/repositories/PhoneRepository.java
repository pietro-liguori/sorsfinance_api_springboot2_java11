package com.vili.sorsfinance.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

	Page<Phone> findByContactOwnerId(Long owner, Pageable pageable);
	Page<Phone> findByType(Integer type, Pageable pageable);
	Page<Phone> findByPreferred(Boolean preferred, Pageable pageable);
	Page<Phone> findByContactOwnerIdAndType(Long owner, Integer type, Pageable pageable);
	Page<Phone> findByContactOwnerIdAndPreferred(Long owner, Boolean preferred, Pageable pageable);
	Page<Phone> findByTypeAndPreferred(Integer type, Boolean preferred, Pageable pageable);
	Page<Phone> findByContactOwnerIdAndTypeAndPreferred(Long owner, Integer type, Boolean preferred, Pageable pageable);
}
