package com.vili.sorsfinance.api.domain.dto;

import java.util.Set;

import com.vili.sorsfinance.api.domain.Account;
import com.vili.sorsfinance.api.domain.BankAccount;
import com.vili.sorsfinance.api.domain.BankCard;
import com.vili.sorsfinance.api.domain.Person;
import com.vili.sorsfinance.api.domain.Voucher;
import com.vili.sorsfinance.api.domain.VoucherAccount;
import com.vili.sorsfinance.api.domain.Wallet;
import com.vili.sorsfinance.api.domain.enums.AccountStatus;
import com.vili.sorsfinance.api.domain.enums.AccountType;
import com.vili.sorsfinance.api.domain.enums.PeriodUnit;
import com.vili.sorsfinance.api.validation.constraints.ValidAccount;
import com.vili.sorsfinance.framework.DataTransferObject;

@ValidAccount
public class AccountDTO extends DataTransferObject {

	private Long holderId;
	private String name;
	private Integer type;
	private Integer status;
	private Long bankId;
	private Double balance;
	private Set<Long> cardIds;
	private Set<Long> voucherIds;
	private String number;
	private String agency;
	private Double overdraft;
	private Double interest;
	private Integer interestUnit;
	private Integer gracePeriod;
	private Integer gracePeriodUnit;
	private Double savings;

	public AccountDTO() {
		super();
	}

	public Long getHolderId() {
		return holderId;
	}

	public void setHolderId(Long holderId) {
		this.holderId = holderId;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public Set<Long> getCardIds() {
		return cardIds;
	}

	public void setCardIds(Set<Long> cardIds) {
		this.cardIds = cardIds;
	}

	public Set<Long> getVoucherIds() {
		return voucherIds;
	}

	public void setVoucherIds(Set<Long> voucherIds) {
		this.voucherIds = voucherIds;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public Double getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(Double overdraft) {
		this.overdraft = overdraft;
	}

	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	public Integer getInterestUnit() {
		return interestUnit;
	}

	public void setInterestUnit(Integer interestUnit) {
		this.interestUnit = interestUnit;
	}

	public Integer getGracePeriod() {
		return gracePeriod;
	}

	public void setGracePeriod(Integer gracePeriod) {
		this.gracePeriod = gracePeriod;
	}

	public Integer getGracePeriodUnit() {
		return gracePeriodUnit;
	}

	public void setGracePeriodUnit(Integer gracePeriodUnit) {
		this.gracePeriodUnit = gracePeriodUnit;
	}

	public Double getSavings() {
		return savings;
	}

	public void setSavings(Double savings) {
		this.savings = savings;
	}
	
	@Override
	public Account toEntity() {
		AccountType type = AccountType.toEnum(getType());

		if (Account.BANK_ACCOUNT_TYPES.contains(type)) {
			Person holder = new Person(getHolderId());
			Person bank = new Person(getBankId());
			BankAccount acc = new BankAccount(getId(), getName(), holder, getNumber(), getAgency(), bank,
					getBalance(), getOverdraft(), getInterest(), PeriodUnit.toEnum(getInterestUnit()),
					getGracePeriod(), PeriodUnit.toEnum(getGracePeriodUnit()), getSavings(),
					AccountType.toEnum(getType()), AccountStatus.toEnum(getStatus()));
			
			for (Long cardId : getCardIds()) {
				acc.addCard(new BankCard(cardId));
			}
			
			return acc;
		}
		
		if (Account.VOUCHER_ACCOUNT_TYPES.contains(type)) {
			Person holder = new Person(getHolderId());
			Person provider = new Person(getBankId());
			VoucherAccount acc = new VoucherAccount(getId(), getName(), getNumber(), holder, provider,
					AccountType.toEnum(getType()), AccountStatus.toEnum(getStatus()));

			for (Long voucherId : getVoucherIds()) {
				acc.addVoucher(new Voucher(voucherId));
			}
			
			return acc;
		}
		
		if (Account.WALLET_TYPES.contains(type)) {
			Person holder = new Person(getHolderId());
			Wallet acc = new Wallet(getId(), getName(), holder, getBalance(), getSavings(),
					AccountType.toEnum(getType()), AccountStatus.toEnum(getStatus()));
			return acc;
		}

		return null;
	}
}
