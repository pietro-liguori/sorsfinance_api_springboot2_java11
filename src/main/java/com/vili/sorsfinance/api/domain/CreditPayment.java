package com.vili.sorsfinance.api.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vili.sorsfinance.api.domain.enums.InstallmentOption;
import com.vili.sorsfinance.api.domain.enums.PaymentStatus;
import com.vili.sorsfinance.api.domain.enums.PaymentType;
import com.vili.sorsfinance.api.repositories.CreditPaymentRepository;
import com.vili.sorsfinance.api.services.CreditPaymentService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.request.annotations.NoFilter;

@Entity
@ServiceRef(CreditPaymentService.class)
@RepositoryRef(CreditPaymentRepository.class)
public class CreditPayment extends Payment {

	private static final long serialVersionUID = 1L;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer installments;
	
	@OneToMany(mappedBy = "payment")
	@NoFilter
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonIgnoreProperties({ "payment" })
	private Set<CreditInstallment> items = new HashSet<>();

	public CreditPayment() {
		super();
		super.setType(PaymentType.CREDIT);
	}

	public CreditPayment(Long id) {
		super(id, CreditPayment.class);
		super.setType(PaymentType.CREDIT);
	}

	public CreditPayment(Long id, String description, Double value, PaymentStatus status, Account account,
			Person responsible, Transaction transaction, Card card, Integer installments) {
		super(id, description, PaymentType.CREDIT, value, status, account, responsible, transaction, card, CreditPayment.class);
		this.installments = installments;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}
	
	@JsonIgnore
	public List<CreditInstallment> getItems() {
		return items.stream().toList();
	}

	public void addItem(CreditInstallment item) {
		items.add(item);
	}

	public void addItems(CreditInstallment... items) {
		for (CreditInstallment x : items) {
			this.items.add(x);
		}
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
