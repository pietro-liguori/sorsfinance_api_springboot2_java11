package com.vili.sorsfinance.api.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.domain.enums.AccountStatus;
import com.vili.sorsfinance.api.domain.enums.AccountType;
import com.vili.sorsfinance.api.repositories.AccountRepository;
import com.vili.sorsfinance.api.services.AccountService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;

@Entity
@ServiceRef(AccountService.class)
@RepositoryRef(AccountRepository.class)
@Inheritance(strategy=InheritanceType.JOINED)
@JsonPropertyOrder({ "id", "name", "type", "number", "agency", "balance", "savings", "overdraft", "creditLimit", "interest", "interestUnit", "gracePeriod", "gracePeriodUnit", "status", "holder", "bank" })
public class Account extends BusinessEntity {
	
	private static final long serialVersionUID = 1L;
	
	public static final List<AccountType> BANK_ACCOUNT_TYPES = Arrays.asList(AccountType.SAVINGS_ACCOUNT, AccountType.SALARY_ACCOUNT, AccountType.CHECKING_ACCOUNT);
	
	public static final List<AccountType> TICKET_ACCOUNT_TYPES = Arrays.asList(AccountType.TICKET_ACCOUNT);
	
	public static final List<AccountType> WALLET_TYPES = Arrays.asList(AccountType.WALLET);

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double balance;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer type;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name="holder_id")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "account", "contact" })
	private Person holder;
	
	@OneToMany(mappedBy = "account")
	@JsonIgnore
	private Set<Payment> payments = new HashSet<>();
	
	public Account() {
		super();
	}
	
	public Account(Long id) {
		super(id, Account.class);
	}

	public Account(Long id, Class<?> domain) {
		super(id, domain);
	}

	public Account(Long id, String name, Person holder, Double balance, AccountType type, AccountStatus status, Class<?> domain) {
		super(id, domain);
		this.name = name;
		this.holder = holder;
		this.balance = balance;
		this.type = type == null ? null : type.getCode();
		this.status = status == null ? null : status.getCode();
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

	public String getType() {
		if (type == null)
			return null;
		
		return AccountType.toEnum(type).getLabel();
	}

	public void setType(AccountType type) {
		this.type = type.getCode();
	}

	public String getStatus() {
		if (status == null)
			return null;
		
		return AccountStatus.toEnum(status).getLabel();
	}

	public void setStatus(AccountStatus status) {
		this.status = status.getCode();
	}

	public Person getHolder() {
		return holder;
	}

	public void setHolder(Person holder) {
		this.holder = holder;
	}

	public Set<Payment> getPayments() {
		return payments;
	}
	
	public void addPayment(Payment payment) {
		payments.add(payment);
	}
	
	public void addPayments(Payment... payments) {
		for (Payment x : payments) {
			this.payments.add(x);
		}
	}
}
