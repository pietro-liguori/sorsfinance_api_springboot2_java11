package com.vili.sorsfinance.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vili.sorsfinance.entities.enums.InstallmentOption;
import com.vili.sorsfinance.entities.enums.PaymentStatus;
import com.vili.sorsfinance.entities.enums.PaymentType;

@Entity
public class CreditPayment extends Payment {

	private static final long serialVersionUID = 1L;
	
	private Integer installments;
	@JsonIgnore
	@OneToMany(mappedBy = "id.payment")
	private Set<PaymentInstallment> items = new HashSet<>();

	public CreditPayment() {
	}

	public CreditPayment(Long id, PaymentType type, Double value, PaymentStatus status, Account account,
			Person responsible, Transaction transaction, Card card, Integer installments) {
		super(id, type, value, status, account, responsible, transaction, card);
		this.installments = installments;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}
	
	@JsonIgnore
	public Set<PaymentInstallment> getItems() {
		return items;
	}

	public void addItem(PaymentInstallment item) {
		items.add(item);
	}

	public void addItems(List<PaymentInstallment> item) {
		for (PaymentInstallment x : item ) {
			items.add(x);
		}
	}
	
	@JsonIgnore
	public List<CreditCardStatement> getStatements() {
		List<CreditCardStatement> list = new ArrayList<>();
		for (PaymentInstallment x : items) {
			list.add(x.getStatement());
		}
		return list;
	}
	
	public List<Double> getInstallmentValues(InstallmentOption option) {
		List<Double> values = new ArrayList<>();
		Double absValue = Math.abs(super.getValue());
		Double xPn = new BigDecimal(absValue / installments).setScale(2, RoundingMode.HALF_UP).doubleValue();
		Double rest = new BigDecimal(absValue - xPn * installments).setScale(2, RoundingMode.HALF_UP).doubleValue();
		Double xRest = xPn + rest;
		Integer restIndex;
		
		if (option == null || option == InstallmentOption.DIFFERENCE_ON_FIRST) restIndex = 1;
		else restIndex = installments;
		
		for (int i = 1; i <= installments; i++) {
			if (i == restIndex) values.add(xRest);
			else values.add(xPn);
		}

		return values;
	}
}
