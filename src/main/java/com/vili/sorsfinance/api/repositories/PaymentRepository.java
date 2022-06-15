package com.vili.sorsfinance.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vili.sorsfinance.api.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	Page<Payment> findByAccountId(Long account, Pageable pageable);
	Page<Payment> findByAccountIdAndResponsibleId(Long account, Long responsible, Pageable pageable);
	Page<Payment> findByAccountIdAndTransactionId(Long account, Long transaction, Pageable pageable);
	Page<Payment> findByAccountIdAndType(Long account, Integer type, Pageable pageable);
	Page<Payment> findByAccountIdAndStatus(Long account, Integer status, Pageable pageable);
	Page<Payment> findByAccountIdAndResponsibleIdAndTransactionId(Long account, Long responsible, Long transaction, Pageable pageable);
	Page<Payment> findByAccountIdAndResponsibleIdAndType(Long account, Long responsible, Integer type, Pageable pageable);
	Page<Payment> findByAccountIdAndResponsibleIdAndStatus(Long account, Long responsible, Integer status, Pageable pageable);
	Page<Payment> findByAccountIdAndTransactionIdAndType(Long account, Long transaction, Integer type, Pageable pageable);
	Page<Payment> findByAccountIdAndTransactionIdAndStatus(Long account, Long transaction, Integer status, Pageable pageable);
	Page<Payment> findByAccountIdAndTypeAndStatus(Long account, Integer type, Integer status, Pageable pageable);
	Page<Payment> findByAccountIdAndResponsibleIdAndTransactionIdAndType(Long account, Long responsible, Long transaction, Integer type, Pageable pageable);
	Page<Payment> findByAccountIdAndResponsibleIdAndTransactionIdAndStatus(Long account, Long responsible, Long transaction, Integer status, Pageable pageable);
	Page<Payment> findByAccountIdAndResponsibleIdAndTypeAndStatus(Long account, Long responsible, Integer type, Integer status, Pageable pageable);
	Page<Payment> findByAccountIdAndTransactionIdAndTypeAndStatus(Long account, Long transaction, Integer type, Integer status, Pageable pageable);
	Page<Payment> findByAccountIdAndResponsibleIdAndTransactionIdAndTypeAndStatus(Long account, Long responsible, Long transaction, Integer type, Integer status, Pageable pageable);
	
	Page<Payment> findByCardId(Long card, Pageable pageable);
	Page<Payment> findByCardIdAndResponsibleId(Long account, Long responsible, Pageable pageable);
	Page<Payment> findByCardIdAndTransactionId(Long account, Long transaction, Pageable pageable);
	Page<Payment> findByCardIdAndType(Long account, Integer type, Pageable pageable);
	Page<Payment> findByCardIdAndStatus(Long account, Integer status, Pageable pageable);
	Page<Payment> findByCardIdAndResponsibleIdAndTransactionId(Long card, Long responsible, Long transaction, Pageable pageable);
	Page<Payment> findByCardIdAndResponsibleIdAndType(Long card, Long responsible, Integer type, Pageable pageable);
	Page<Payment> findByCardIdAndResponsibleIdAndStatus(Long card, Long responsible, Integer status, Pageable pageable);
	Page<Payment> findByCardIdAndTransactionIdAndType(Long card, Long transaction, Integer type, Pageable pageable);
	Page<Payment> findByCardIdAndTransactionIdAndStatus(Long card, Long transaction, Integer status, Pageable pageable);
	Page<Payment> findByCardIdAndTypeAndStatus(Long card, Integer type, Integer status, Pageable pageable);
	Page<Payment> findByCardIdAndResponsibleIdAndTransactionIdAndType(Long card, Long responsible, Long transaction, Integer type, Pageable pageable);
	Page<Payment> findByCardIdAndResponsibleIdAndTransactionIdAndStatus(Long card, Long responsible, Long transaction, Integer status, Pageable pageable);
	Page<Payment> findByCardIdAndResponsibleIdAndTypeAndStatus(Long card, Long responsible, Integer type, Integer status, Pageable pageable);
	Page<Payment> findByCardIdAndTransactionIdAndTypeAndStatus(Long card, Long transaction, Integer type, Integer status, Pageable pageable);
	Page<Payment> findByCardIdAndResponsibleIdAndTransactionIdAndTypeAndStatus(Long card, Long responsible, Long transaction, Integer type, Integer status, Pageable pageable);
	
	Page<Payment> findByResponsibleId(Long responsible, Pageable pageable);
	Page<Payment> findByTransactionId(Long transaction, Pageable pageable);
	Page<Payment> findByType(Integer type, Pageable pageable);
	Page<Payment> findByStatus(Integer status, Pageable pageable);
	Page<Payment> findByResponsibleIdAndTransactionId(Long responsible, Long transaction, Pageable pageable);
	Page<Payment> findByResponsibleIdAndType(Long responsible, Integer type, Pageable pageable);
	Page<Payment> findByResponsibleIdAndStatus(Long responsible, Integer status, Pageable pageable);
	Page<Payment> findByTransactionIdAndType(Long transaction, Integer type, Pageable pageable);
	Page<Payment> findByTransactionIdAndStatus(Long transaction, Integer status, Pageable pageable);
	Page<Payment> findByTypeAndStatus(Integer type, Integer status, Pageable pageable);
	Page<Payment> findByResponsibleIdAndTransactionIdAndType(Long responsible, Long transaction, Integer type, Pageable pageable);
	Page<Payment> findByResponsibleIdAndTransactionIdAndStatus(Long responsible, Long transaction, Integer status, Pageable pageable);
	Page<Payment> findByResponsibleIdAndTypeAndStatus(Long responsible, Integer type, Integer status, Pageable pageable);
	Page<Payment> findByTransactionIdAndTypeAndStatus(Long transaction, Integer type, Integer status, Pageable pageable);
	Page<Payment> findByResponsibleIdAndTransactionIdAndTypeAndStatus(Long responsible, Long transaction, Integer type, Integer status, Pageable pageable);
}
