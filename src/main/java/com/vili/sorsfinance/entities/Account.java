package com.vili.sorsfinance.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.vili.sorsfinance.entities.enums.AccountStatus;
import com.vili.sorsfinance.entities.enums.AccountType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Account implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Double balance;
	private Integer type;
	private Integer status;
	@ManyToOne
	@JoinColumn(name="holder_id")
	private Person holder;
	
	public Account() {
	}

	public Account(Long id, String name, Person holder, Double balance, AccountType type, AccountStatus status) {
		super();
		this.id = id;
		this.name = name;
		this.holder = holder;
		this.balance = balance;
		this.type = type.getCode();
		this.status = status.getCode();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public AccountType getType() {
		return AccountType.toEnum(type);
	}

	public void setType(AccountType type) {
		this.type = type.getCode();
	}

	public AccountStatus getStatus() {
		return AccountStatus.toEnum(status);
	}

	public void setStatus(AccountStatus status) {
		this.status = status.getCode();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(id, other.id);
	}
}
