package com.vili.sorsfinance.api.framework;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vili.sorsfinance.api.entities.Account;
import com.vili.sorsfinance.api.entities.BankAccount;
import com.vili.sorsfinance.api.entities.Card;
import com.vili.sorsfinance.api.entities.Person;
import com.vili.sorsfinance.api.entities.TicketAccount;
import com.vili.sorsfinance.api.entities.Wallet;
import com.vili.sorsfinance.api.entities.dto.AccountDTO;
import com.vili.sorsfinance.api.entities.enums.AccountStatus;
import com.vili.sorsfinance.api.entities.enums.AccountType;
import com.vili.sorsfinance.api.entities.enums.PeriodUnit;

@Entity
@Table(name = "entities")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BusEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnore
	private Class<?> sorsClass;
	@JsonIgnore
	private Date createdAt;
	@JsonIgnore
	private Date updatedAt;
	@JsonIgnore
	private boolean active;
	
	public BusEntity() {
		super();
		this.active = true;
	}

	public BusEntity(Long id, Class<?> sorsClass) {
		super();
		this.id = id;
		this.sorsClass = sorsClass;
		this.active = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Class<?> getSorsClass() {
		return sorsClass;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date date) {
		this.createdAt = date;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date date) {
		this.updatedAt = date;
	}

	public boolean isActive() {
		return active;
	}

	public void setAsActive() {
		this.active = true;
	}
	
	public void setAsInactive() {
		this.active = false;
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
		BusEntity other = (BusEntity) obj;
		return Objects.equals(id, other.id);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends BusEntity> T fromDTO(DTO<T> dto) {
		if (dto instanceof AccountDTO) {
			AccountDTO obj = (AccountDTO) dto;
			AccountType type = AccountType.toEnum(((AccountDTO) dto).getType());

			if (Account.BANK_ACCOUNT_TYPES.contains(type)) {
				BankAccount acc = new BankAccount(null, obj.getName(), null, obj.getNumber(), obj.getAgency(), null,
						obj.getBalance(), obj.getOverdraft(), obj.getInterest(), PeriodUnit.toEnum(obj.getInterestUnit()),
						obj.getGracePeriod(), PeriodUnit.toEnum(obj.getGracePeriodUnit()), obj.getCreditLimit(),
						AccountType.toEnum(obj.getType()), AccountStatus.toEnum(obj.getStatus()));
				Person holder = new Person(obj.getHolderId());
				Person bank = new Person(obj.getBankId());
				acc.setHolder(holder);
				acc.setBank(bank);
				
				for (Long cardId : obj.getCards()) {
					acc.addCard(new Card(cardId));
				}
				
				acc.setCreatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));
				acc.setUpdatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));
				
				return (T) acc;
			}
			
			if (Account.TICKET_ACCOUNT_TYPES.contains(type)) {
				TicketAccount acc = new TicketAccount(null, obj.getName(), null, null, obj.getBalance(),
						AccountType.toEnum(obj.getType()), AccountStatus.toEnum(obj.getStatus()));
				Person holder = new Person(obj.getHolderId());
				Person bank = new Person(obj.getBankId());
				acc.setHolder(holder);
				acc.setBank(bank);

				for (Long cardId : obj.getCards()) {
					acc.addCard(new Card(cardId));
				}

				acc.setCreatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));
				acc.setUpdatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));

				return (T) acc;
			}
			
			if (Account.WALLET_TYPES.contains(type)) {
				Wallet acc = new Wallet(null, obj.getName(), null, obj.getBalance(), obj.getSavings(), AccountType.toEnum(obj.getType()),
						AccountStatus.toEnum(obj.getStatus()));
				Person holder = new Person(obj.getHolderId());
				acc.setHolder(holder);
				acc.setCreatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));
				acc.setUpdatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));

				return (T) acc;
			}
			
			return null;
		}
		
		return null;
	}
}
