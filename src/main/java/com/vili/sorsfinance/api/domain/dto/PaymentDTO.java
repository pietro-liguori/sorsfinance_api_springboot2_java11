package com.vili.sorsfinance.api.domain.dto;

import com.vili.sorsfinance.api.domain.Account;
import com.vili.sorsfinance.api.domain.BankAccount;
import com.vili.sorsfinance.api.domain.Card;
import com.vili.sorsfinance.api.domain.CreditCard;
import com.vili.sorsfinance.api.domain.CreditPayment;
import com.vili.sorsfinance.api.domain.NaturalPerson;
import com.vili.sorsfinance.api.domain.Payment;
import com.vili.sorsfinance.api.domain.Person;
import com.vili.sorsfinance.api.domain.Transaction;
import com.vili.sorsfinance.api.domain.enums.PaymentStatus;
import com.vili.sorsfinance.api.domain.enums.PaymentType;
import com.vili.sorsfinance.framework.DataTransferObject;

public class PaymentDTO extends DataTransferObject {

	private String description;
	
	private Double value;
	
	private Integer type;
	
	private Integer status;
	
	private Long accountId;
	
	private Long responsibleId;
	
	private Long transactionId;
	
	private Long transferAccountId;
	
	private Long creditCardStatementId;
	
	private Long cardId;
	
	private Integer installments;

	public PaymentDTO() {
		super();
	}

	public Long getResponsibleId() {
		return responsibleId;
	}

	public void setResponsibleId(Long responsibleId) {
		this.responsibleId = responsibleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
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

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardIds(Long cardId) {
		this.cardId = cardId;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getTransferAccountId() {
		return transferAccountId;
	}

	public void setTransferAccountId(Long transferAccountId) {
		this.transferAccountId = transferAccountId;
	}

	public Long getCreditCardStatementId() {
		return creditCardStatementId;
	}

	public void setCreditCardStatementId(Long creditCardStatementId) {
		this.creditCardStatementId = creditCardStatementId;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}
	
	@Override
	public Payment toEntity() {
		PaymentType type = PaymentType.toEnum(getType());

		if (Payment.INSTALLMENT_PAYMENT_TYPES.contains(type)) {
			BankAccount acc = new BankAccount(getAccountId());
			Person resp = new NaturalPerson(getResponsibleId());
			CreditCard card = getCardId() == null ? null : new CreditCard(getCardId());
			Transaction trans = new Transaction(getTransactionId());
			CreditPayment pay = new CreditPayment(getId(), getDescription(), getValue(),
					PaymentStatus.toEnum(getStatus()), acc, resp, trans, card, getInstallments());
			return pay;
		} else {
			Account acc = new Account(getAccountId());
			Person resp = new NaturalPerson(getResponsibleId());
			Card card = getCardId() == null ? null : new Card(getCardId());
			Transaction trans = new Transaction(getTransactionId());
			Payment pay = new Payment(getId(), getDescription(), type, getValue(),
					PaymentStatus.toEnum(getStatus()), acc, resp, trans, card);
			return pay;
		}
	}
}
