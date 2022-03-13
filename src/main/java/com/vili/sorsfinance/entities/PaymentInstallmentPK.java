package com.vili.sorsfinance.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PaymentInstallmentPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "payment_id")
	private Payment payment;
	@ManyToOne
	@JoinColumn(name = "statement_id")
	private CreditCardStatement statement;
	
	public Payment getPayment() {
		return payment;
	}
	
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public CreditCardStatement getStatement() {
		return statement;
	}
	
	public void setStatement(CreditCardStatement statement) {
		this.statement = statement;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(payment, statement);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentInstallmentPK other = (PaymentInstallmentPK) obj;
		return Objects.equals(statement, other.statement) && Objects.equals(payment, other.payment);
	}
}
