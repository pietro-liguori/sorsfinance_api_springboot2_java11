package com.vili.sorsfinance.api.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.domain.enums.PaymentStatus;
import com.vili.sorsfinance.api.domain.enums.PeriodUnit;
import com.vili.sorsfinance.api.repositories.BankslipRepository;
import com.vili.sorsfinance.api.services.BankslipService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.request.annotations.NoFilter;

@Entity
@ServiceRef(value = BankslipService.class)
@RepositoryRef(value = BankslipRepository.class)
@JsonPropertyOrder({ "id", "value", "installment", "dueDate", "payDate", "fine", "fineUnit", "interest", "interestUnit", "origin", "payment" })
public class Bankslip extends BusinessEntity {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String number;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double value;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer installment;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date dueDate;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date payDate;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double fine;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer fineUnit;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double interest;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer interestUnit;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer status;

	@OneToOne
	@JoinColumn(name = "payment_id")
	@NoFilter
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Payment payment;

	@OneToOne
	@JoinColumn(name = "origin_id")
	@NoFilter
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Payment origin;

	public Bankslip() {
		super();
	}

	public Bankslip(Long id) {
		super(id, Bankslip.class);
	}

	public Bankslip(Long id, Payment origin, String number,Double value, Integer installment, Date dueDate, Double fine, PeriodUnit fineUnit, Double interest, PeriodUnit interestUnit) {
		super(id, Bankslip.class);
		this.number = number;
		this.origin = origin;
		this.value = value;
		this.installment = installment;
		this.dueDate = dueDate;
		this.fine = fine;
		this.fineUnit = fineUnit == null ? null : fineUnit.getCode();
		this.interest = interest;
		this.installment = interestUnit == null ? null : interestUnit.getCode();
		this.status = PaymentStatus.NOT_PAID.getCode();
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Double getFine() {
		return fine;
	}

	public void setFine(Double fine) {
		this.fine = fine;
	}

	public Integer getFineUnit() {
		return fineUnit;
	}

	public void setFineUnit(PeriodUnit fineUnit) {
		this.fineUnit = fineUnit == null ? null : fineUnit.getCode();
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

	public void setInterestUnit(PeriodUnit interestUnit) {
		this.interestUnit = interestUnit == null ? null : interestUnit.getCode();
	}

	public Payment getOrigin() {
		return origin;
	}

	public void setOrigin(Payment origin) {
		this.origin = origin;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status == null ? null : status.getCode();
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public Double getUpdatedTotal() {
		// TODO
		
		return getValue();
	}
}
