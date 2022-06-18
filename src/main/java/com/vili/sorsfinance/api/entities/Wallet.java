package com.vili.sorsfinance.api.entities;

import java.util.Date;

import javax.persistence.Entity;

import com.vili.sorsfinance.api.entities.dto.AccountDTO;
import com.vili.sorsfinance.api.entities.enums.AccountStatus;
import com.vili.sorsfinance.api.entities.enums.AccountType;
import com.vili.sorsfinance.api.framework.DTOType;

@Entity
public class Wallet extends Account {

	private static final long serialVersionUID = 1L;

	private Double savings;

	public Wallet() {
		super();
	}

	public Wallet(Long id, String name, Person holder, Double balance, Double savings, AccountType type,
			AccountStatus status) {
		super(id, name, holder, balance, type, status, Wallet.class);
		this.savings = savings;
	}

	public Double getSavings() {
		return savings;
	}

	public void setSavings(Double savings) {
		this.savings = savings;
	}

	public static Wallet fromDTO(AccountDTO dto) {
		Person holder = new Person(dto.getHolderId());
		Wallet acc = new Wallet(dto.getId(), dto.getName(), holder, dto.getBalance(), dto.getSavings(),
				AccountType.toEnum(dto.getType()), AccountStatus.toEnum(dto.getStatus()));

		if (dto.getMethod().equals(DTOType.INSERT))
			acc.setCreatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));
		
		acc.setUpdatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));

		return acc;
	}
}
