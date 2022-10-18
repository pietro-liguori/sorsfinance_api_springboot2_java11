package com.vili.sorsfinance.api.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vili.sorsfinance.api.domain.enums.AccountStatus;
import com.vili.sorsfinance.api.domain.enums.AccountType;
import com.vili.sorsfinance.api.repositories.VoucherAccountRepository;
import com.vili.sorsfinance.api.services.VoucherAccountService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.request.annotations.FilterSetting;
import com.vili.sorsfinance.framework.request.annotations.NoFilter;

@Entity
@ServiceRef(VoucherAccountService.class)
@RepositoryRef(VoucherAccountRepository.class)
public class VoucherAccount extends Account {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String number;

	@ManyToOne
	@JoinColumn(name = "bank_id")
	@FilterSetting(nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Person bank;
	
	@OneToMany(mappedBy = "account")
	@NoFilter
	private Set<Card> vouchers = new HashSet<>();

	public VoucherAccount() {
		super();
	}

	public VoucherAccount(Long id) {
		super(id, VoucherAccount.class);
	}
	
	public VoucherAccount(Long id, String name, String number, Person holder, Person bank, AccountType type,
			AccountStatus status) {
		super(id, name, holder, type, status, VoucherAccount.class);
		this.number = number;
		this.bank = bank;
	}

	protected VoucherAccount(Long id, Class<?> domain) {
		super(id, domain);
	}

	protected VoucherAccount(Long id, String name, String number, Person holder, Person bank, AccountType type,
			AccountStatus status, Class<?> domain) {
		super(id, name, holder, type, status, domain);
		this.number = number;
		this.bank = bank;
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Person getBank() {
		return bank;
	}

	public void setBank(Person bank) {
		this.bank = bank;
	}

	@JsonGetter
	@JsonIgnoreProperties({ "account" })
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	public List<Voucher> getVouchers() {
		List<Voucher> ret  = new ArrayList<>();
		
		for (Card card : vouchers) {
			if (Voucher.class.isAssignableFrom(card.getClass()))
				ret.add((Voucher) card);
		}
		
		return ret;
	}

	public void addVoucher(Voucher voucher) {
		vouchers.add(voucher);
	}

	public void addVouchers(Voucher... voucher) {
		for (Voucher x : voucher) {
			this.vouchers.add(x);
		}
	}
}
