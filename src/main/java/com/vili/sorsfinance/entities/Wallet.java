package com.vili.sorsfinance.entities;

import javax.persistence.Entity;

import com.vili.sorsfinance.entities.enums.AccountStatus;
import com.vili.sorsfinance.entities.enums.AccountType;

@Entity
public class Wallet extends Account {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public Wallet() {
		super();
	}

	public Wallet(Long id, Person holder, String name, Double balance, AccountType type, AccountStatus status) {
		super(id, holder, balance, type, status);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
