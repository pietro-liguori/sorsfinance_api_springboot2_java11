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
import com.vili.sorsfinance.framework.request.annotations.FilterSetting;
import com.vili.sorsfinance.framework.request.annotations.NoFilter;

@Entity
@ServiceRef(AccountService.class)
@RepositoryRef(AccountRepository.class)
@Inheritance(strategy=InheritanceType.JOINED)
@JsonPropertyOrder({ "id", "name", "type", "number", "agency", "balance", "savings", "overdraft", "interest", "interestUnit", "gracePeriod", "gracePeriodUnit", "status", "holder", "bank" })
public class Account extends BusinessEntity {
	
	private static final long serialVersionUID = 1L;
	
	public static final List<AccountType> BANK_ACCOUNT_TYPES = Arrays.asList(AccountType.SAVINGS_ACCOUNT, AccountType.SALARY_ACCOUNT, AccountType.CHECKING_ACCOUNT);
	
	public static final List<AccountType> VOUCHER_ACCOUNT_TYPES = Arrays.asList(AccountType.VOUCHER_ACCOUNT);
	
	public static final List<AccountType> WALLET_TYPES = Arrays.asList(AccountType.WALLET);

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer type;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name="holder_id")
	@FilterSetting(nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "account", "contact" })
	private Person holder;
	
	@OneToMany(mappedBy = "account")
	@NoFilter
	@JsonIgnore
	private Set<Payment> payments = new HashSet<>();
	
	public Account() {
		super();
	}
	
	public Account(Long id) {
		super(id, Account.class);
	}

	protected Account(Long id, Class<?> domain) {
		super(id, domain);
	}

	protected Account(Long id, String name, Person holder, AccountType type, AccountStatus status, Class<?> domain) {
		super(id, domain);
		this.name = name;
		this.holder = holder;
		this.type = type == null ? null : type.getCode();
		this.status = status == null ? null : status.getCode();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type == null ? null : type.getCode();
	}

	public AccountStatus getStatus() {
		return status == null ? null : AccountStatus.toEnum(status);
	}

	public void setStatus(AccountStatus status) {
		this.status = status == null ? null : status.getCode();
	}

	public Person getHolder() {
		return holder;
	}

	public void setHolder(Person holder) {
		this.holder = holder;
	}

	public List<Payment> getPayments() {
		return payments.stream().toList();
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
