package com.vili.sorsfinance.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vili.sorsfinance.entities.enums.CardStatus;
import com.vili.sorsfinance.entities.enums.CardType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Card implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String number;
	private Date expiration;
	private Integer type;
	private Integer status;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	
	public Card() {
	}

	public Card(Long id, String name, Account account, String number, Date expiration, CardType type, CardStatus status) {
		super();
		this.id = id;
		this.name = name;
		this.account = account;
		this.number = number;
		this.expiration = expiration;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public CardType getType() {
		return CardType.toEnum(type);
	}

	public void setType(CardType type) {
		this.type = type.getCode();
	}

	public CardStatus getStatus() {
		return CardStatus.toEnum(status);
	}

	public void setStatus(CardStatus status) {
		this.status = status.getCode();
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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
		Card other = (Card) obj;
		return Objects.equals(id, other.id);
	}
}
