package com.vili.sorsfinance.api.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.domain.enums.PaymentStatus;
import com.vili.sorsfinance.api.repositories.CreditCardStatementRepository;
import com.vili.sorsfinance.api.services.CreditCardStatementService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.request.annotations.FilterSetting;
import com.vili.sorsfinance.framework.request.annotations.NoFilter;

@Entity
@ServiceRef(value = CreditCardStatementService.class)
@RepositoryRef(value = CreditCardStatementRepository.class)
@JsonPropertyOrder({ "id", "description", "closingDate", "dueDate", "total", "payDate", "paidValue", "status", "payment", "account", "card", "items" })
public class CreditCardStatement extends BusinessEntity {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String description;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date closingDate;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date dueDate;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date payDate;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double paidValue;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer status;
	
	@OneToOne
	@JoinColumn(name = "payment_id")
	@NoFilter
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Payment payment;

	@ManyToOne
	@JoinColumn(name = "account_id")
	@FilterSetting(nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private BankAccount account;
	
	@OneToMany(mappedBy = "statement")
	@NoFilter
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonIgnoreProperties({ "statement" })
	private Set<CreditInstallment> items = new HashSet<>();

	public CreditCardStatement() {
		super();
	}

	public CreditCardStatement(Long id) {
		super(id, CreditCardStatement.class);
	}

	public CreditCardStatement(BankAccount account, String description) {
		super(null, CreditCardStatement.class);
		this.account = account;
		this.description = description;
	}

	public CreditCardStatement(Long id, BankAccount account, String description, Date closingDate, Date dueDate) {
		super(id, CreditCardStatement.class);
		this.account = account;
		this.description = description;
		this.closingDate = closingDate;
		this.dueDate = dueDate;
		this.status = PaymentStatus.NOT_PAID.getCode();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
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

	public Double getPaidValue() {
		return paidValue;
	}

	public void setPaidValue(Double paidValue) {
		this.paidValue = paidValue;
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

	public BankAccount getAccount() {
		return account;
	}

	public void setAccount(BankAccount account) {
		this.account = account;
	}

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
	
	public Double getTotal() {
		Double total = 0.0;
		
		for (CreditInstallment item : items) {
			total += item.getValue();
		}
		
		return total;
	}
	
	public Double getUpdatedTotal() {
		// TODO
		
		return getTotal();
	}
}
