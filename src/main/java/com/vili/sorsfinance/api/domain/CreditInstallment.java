package com.vili.sorsfinance.api.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.repositories.CreditInstallmentRepository;
import com.vili.sorsfinance.api.services.CreditInstallmentService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.request.annotations.FilterSetting;
import com.vili.sorsfinance.framework.request.annotations.NoFilter;

@Entity
@ServiceRef(value = CreditInstallmentService.class)
@RepositoryRef(value = CreditInstallmentRepository.class)
@JsonPropertyOrder({ "id", "value", "installment", "payment", "statement" })
public class CreditInstallment extends BusinessEntity {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double value;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer installment;
	
	@ManyToOne
	@JoinColumn(name = "payment_id")
	@FilterSetting(nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "account", "responsible" })
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name = "statement_id")
	@NoFilter
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "items" })
	private CreditCardStatement statement;
	
	public CreditInstallment() {
		super();
	}

	public CreditInstallment(Long id) {
		super(id, CreditInstallment.class);
	}

	public CreditInstallment(Long id, Payment payment, CreditCardStatement statement, Double value, Integer installment) {
		super(id, CreditInstallment.class);
		this.payment = payment;
		this.statement = statement;
		this.value = value;
		this.installment = installment;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getInstallment() {
		return installment;
	}

	public void setInstallment(Integer installment) {
		this.installment = installment;
	}

	public CreditCardStatement getStatement() {
		return statement;
	}
	
	public void setStatement(CreditCardStatement statement) {
		this.statement = statement;
	}
	
	public Payment getPayment() {
		return payment;
	}
	
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}
