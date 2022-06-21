package com.vili.sorsfinance.api.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vili.sorsfinance.api.entities.dto.AccountDTO;
import com.vili.sorsfinance.api.entities.enums.AccountStatus;
import com.vili.sorsfinance.api.entities.enums.AccountType;
import com.vili.sorsfinance.api.framework.DTOType;

@Entity
public class TicketAccount extends Account {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "bank_id")
	private Person bank;
	@OneToMany(mappedBy = "account")
	@JsonIgnoreProperties({ "account" })
	private Set<Card> cards = new HashSet<>();

	public TicketAccount() {
		super();
	}

	public TicketAccount(Long id, String name, Person holder, Person bank, Double balance, AccountType type,
			AccountStatus status) {
		super(id, name, holder, balance, type, status, TicketAccount.class);
		this.bank = bank;
	}

	public Person getBank() {
		return bank;
	}

	public TicketAccount setBank(Person bank) {
		this.bank = bank;
		return this;
	}

	public Set<Card> getCards() {
		return cards;
	}

	public TicketAccount addCard(Card card) {
		cards.add(card);
		return this;
	}

	public TicketAccount addCards(Card... cards) {
		for (Card x : cards) {
			this.cards.add(x);
		}
		return this;
	}

	public static TicketAccount fromDTO(AccountDTO dto) {
		Person holder = new Person(dto.getHolderId());
		Person bank = new Person(dto.getBankId());
		TicketAccount acc = new TicketAccount(dto.getId(), dto.getName(), holder, bank, dto.getBalance(),
				AccountType.toEnum(dto.getType()), AccountStatus.toEnum(dto.getStatus()));

		for (Long cardId : dto.getCardIds()) {
			acc.addCard(new Card(cardId));
		}

		if (dto.getMethod().equals(DTOType.INSERT))
			acc.setCreatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));
		
		acc.setUpdatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));

		return acc;
	} 
}
