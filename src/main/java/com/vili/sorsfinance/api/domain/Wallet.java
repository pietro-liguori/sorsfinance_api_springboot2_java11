package com.vili.sorsfinance.api.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vili.sorsfinance.api.domain.enums.AccountStatus;
import com.vili.sorsfinance.api.domain.enums.AccountType;
import com.vili.sorsfinance.api.repositories.WalletRepository;
import com.vili.sorsfinance.api.services.WalletService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;

@Entity
@ServiceRef(WalletService.class)
@RepositoryRef(WalletRepository.class)
public class Wallet extends Account {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double savings;

	public Wallet() {
		super();
	}

	public Wallet(Long id, String name, Person holder, Double balance, Double savings, AccountType type, AccountStatus status) {
		super(id, name, holder, balance, type, status, Wallet.class);
		this.savings = savings;
	}

	public Double getSavings() {
		return savings;
	}

	public void setSavings(Double savings) {
		this.savings = savings;
	}
}
