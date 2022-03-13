package com.vili.sorsfinance.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vili.sorsfinance.entities.enums.PaymentStatus;

@Entity
public class PaymentInstallment implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId	
	private PaymentInstallmentPK id = new PaymentInstallmentPK();
	private Double value;
	private Integer installments;
	private Integer status;
	
	public PaymentInstallment() {
	}

	public PaymentInstallment(Payment payment, CreditCardStatement statement, Double value, Integer installments, PaymentStatus status) {
		super();
		id.setPayment(payment);
		id.setStatement(statement);
		this.value = value;
		this.installments = installments;
		this.status = status.getCode();
	}

	public PaymentInstallmentPK getId() {
		return id;
	}

	public void setId(PaymentInstallmentPK id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}

	public PaymentStatus getStatus() {
		return PaymentStatus.toEnum(status);
	}

	public void setStatus(PaymentStatus status) {
		this.status = status.getCode();
	}

	@JsonIgnore
	public CreditCardStatement getStatement() {
		return id.getStatement();
	}
	
	@JsonIgnore
	public Payment getPayment() {
		return id.getPayment();
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
		PaymentInstallment other = (PaymentInstallment) obj;
		return Objects.equals(id, other.id);
	}
}
