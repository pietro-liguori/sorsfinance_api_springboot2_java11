package com.vili.sorsfinance.api.domain.dto;

import com.vili.sorsfinance.api.domain.Account;
import com.vili.sorsfinance.api.domain.Card;
import com.vili.sorsfinance.api.domain.CreditPayment;
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

	public PaymentDTO setResponsibleId(Long responsibleId) {
		this.responsibleId = responsibleId;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public PaymentDTO setDescription(String description) {
		this.description = description;
		return this;
	}

	public Double getValue() {
		return value;
	}

	public PaymentDTO setValue(Double value) {
		this.value = value;
		return this;
	}

	public Integer getType() {
		return type;
	}

	public PaymentDTO setType(Integer type) {
		this.type = type;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public PaymentDTO setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public Long getAccountId() {
		return accountId;
	}

	public PaymentDTO setAccountId(Long accountId) {
		this.accountId = accountId;
		return this;
	}

	public Long getCardId() {
		return cardId;
	}

	public PaymentDTO setCardIds(Long cardId) {
		this.cardId = cardId;
		return this;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public PaymentDTO setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
		return this;
	}

	public Long getTransferAccountId() {
		return transferAccountId;
	}

	public PaymentDTO setTransferAccountId(Long transferAccountId) {
		this.transferAccountId = transferAccountId;
		return this;
	}

	public Long getCreditCardStatementId() {
		return creditCardStatementId;
	}

	public PaymentDTO setCreditCardStatementId(Long creditCardStatementId) {
		this.creditCardStatementId = creditCardStatementId;
		return this;
	}

	public Integer getInstallments() {
		return installments;
	}

	public PaymentDTO setInstallments(Integer installments) {
		this.installments = installments;
		return this;
	}
	
	@Override
	public Payment toEntity() {
		PaymentType type = PaymentType.toEnum(getType());

		if (Payment.INSTALLMENT_PAYMENT_TYPES.contains(type)) {
			Account acc = new Account(getAccountId());
			Person resp = new Person(getResponsibleId());
			Card card = getCardId() == null ? null : new Card(getCardId());
			Transaction trans = new Transaction(getTransactionId());
			CreditPayment pay = new CreditPayment(getId(), getDescription(), type, getValue(),
					PaymentStatus.toEnum(getStatus()), acc, resp, trans, card, getInstallments());
			return pay;
		} else {
			Account acc = new Account(getAccountId());
			Person resp = new Person(getResponsibleId());
			Card card = getCardId() == null ? null : new Card(getCardId());
			Transaction trans = new Transaction(getTransactionId());
			Payment pay = new Payment(getId(), getDescription(), type, getValue(),
					PaymentStatus.toEnum(getStatus()), acc, resp, trans, card);
			return pay;
		}
	}
}
