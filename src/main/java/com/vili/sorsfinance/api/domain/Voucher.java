package com.vili.sorsfinance.api.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vili.sorsfinance.api.domain.enums.CardStatus;
import com.vili.sorsfinance.api.domain.enums.CardType;
import com.vili.sorsfinance.api.repositories.VoucherRepository;
import com.vili.sorsfinance.api.services.VoucherService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;

@Entity
@ServiceRef(VoucherService.class)
@RepositoryRef(VoucherRepository.class)
public class Voucher extends Card {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double balance;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date expiration;

	public Voucher() {
		super();
	}

	public Voucher(Long id) {
		super(id, Voucher.class);
	}

	public Voucher(Long id, String name, Account account, String number, Date expiration, Double balance, CardType type,
			CardStatus status) {
		super(id, name, account, number, type, status, Voucher.class);
		this.balance = balance;
		this.expiration = expiration;
	}
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
}
