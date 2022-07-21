package com.vili.sorsfinance.api.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vili.sorsfinance.api.domain.enums.AccountStatus;
import com.vili.sorsfinance.api.domain.enums.AccountType;
import com.vili.sorsfinance.api.repositories.TicketAccountRepository;
import com.vili.sorsfinance.api.services.TicketAccountService;
import com.vili.sorsfinance.framework.annotations.FilterSetting;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;

@Entity
@ServiceRef(TicketAccountService.class)
@RepositoryRef(TicketAccountRepository.class)
public class TicketAccount extends Account {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "bank_id")
	@FilterSetting(nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Person bank;
	
	@OneToMany(mappedBy = "account")
	@FilterSetting(alias = "card", nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
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

	public void setBank(Person bank) {
		this.bank = bank;
	}

	public Set<Card> getCards() {
		return cards;
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public void addCards(Card... cards) {
		for (Card x : cards) {
			this.cards.add(x);
		}
	}
}
