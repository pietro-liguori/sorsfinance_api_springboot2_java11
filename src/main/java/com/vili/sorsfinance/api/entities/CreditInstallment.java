package com.vili.sorsfinance.api.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.entities.enums.PaymentStatus;
import com.vili.sorsfinance.api.framework.BusEntity;

@Entity
@JsonPropertyOrder({ "id", "value", "installment", "status", "payment", "statement" })
public class CreditInstallment extends BusEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "payment_id")
	@JsonIgnoreProperties({ "account", "card", "responsible" })
	private Payment payment;
	@ManyToOne
	@JoinColumn(name = "statement_id")
	@JsonIgnoreProperties({ "items" })
	private CreditCardStatement statement;
	private Double value;
	private Integer installment;
	private Integer status;
	
	public CreditInstallment() {
		super();
	}

	public CreditInstallment(Long id,Payment payment, CreditCardStatement statement, Double value, Integer installment, PaymentStatus status) {
		super(id, CreditInstallment.class);
		this.payment = payment;
		this.statement = statement;
		this.value = value;
		this.installment = installment;
		this.status = status.getCode();
	}

	public Double getValue() {
		return value;
	}

	public CreditInstallment setValue(Double value) {
		this.value = value;
		return this;
	}

	public Integer getInstallment() {
		return installment;
	}

	public CreditInstallment setInstallment(Integer installments) {
		this.installment = installments;
		return this;
	}

	public String getStatus() {
		return PaymentStatus.toEnum(status).getLabel();
	}

	public CreditInstallment setStatus(PaymentStatus status) {
		this.status = status.getCode();
		return this;
	}

	public CreditCardStatement getStatement() {
		return statement;
	}
	
	public Payment getPayment() {
		return payment;
	}
}
