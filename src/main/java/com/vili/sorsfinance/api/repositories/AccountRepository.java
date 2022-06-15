package com.vili.sorsfinance.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Page<Account> findByHolderId(Long holder, Pageable pageable);
	Page<Account> findByType(Integer type, Pageable pageable);
	Page<Account> findByStatus(Integer status, Pageable pageable);
	Page<Account> findByHolderIdAndType(Long holder, Integer type, Pageable pageable);
	Page<Account> findByHolderIdAndStatus(Long holder, Integer status, Pageable pageable);
	Page<Account> findByTypeAndStatus(Integer type, Integer status, Pageable pageable);
	Page<Account> findByHolderIdAndTypeAndStatus(Long holder, Integer type, Integer status, Pageable pageable);
}
