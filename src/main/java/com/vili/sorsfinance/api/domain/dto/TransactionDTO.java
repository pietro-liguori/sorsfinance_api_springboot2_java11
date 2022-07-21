package com.vili.sorsfinance.api.domain.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.vili.sorsfinance.api.domain.Category;
import com.vili.sorsfinance.api.domain.Person;
import com.vili.sorsfinance.api.domain.Transaction;
import com.vili.sorsfinance.api.domain.enums.TransactionType;
import com.vili.sorsfinance.api.validation.constraints.ValidCategoryId;
import com.vili.sorsfinance.api.validation.constraints.ValidEnumValue;
import com.vili.sorsfinance.api.validation.constraints.ValidPersonId;
import com.vili.sorsfinance.api.validation.constraints.ValidTransaction;
import com.vili.sorsfinance.api.validation.constraints.ValidTransactionItem;
import com.vili.sorsfinance.framework.DataTransferObject;

@ValidTransaction
public class TransactionDTO extends DataTransferObject {

	@NotNull(message = "Must not be null")
	private Date date;
	@NotBlank(message = "Must not be null or empty")
	@Length(min = 5, max = 60, message = "Must be between 5 and 60 characters")
	private String description;
	@NotNull(message = "Must not be null")
	@PositiveOrZero(message = "Must be positive or zero")
	private Double discount;
	@NotNull(message = "Must not be null")
	@PositiveOrZero(message = "Must be positive or zero")
	private Double total;
	@ValidEnumValue(target = TransactionType.class)
	private Integer type;
	@ValidPersonId(acceptAll = true)
	private Long recipientId;
	@NotEmpty(message = "Must not be null or empty")
	private Set<@ValidCategoryId Long> categoryIds;
	@NotEmpty(message = "Must not be null or empty")
	private Set<@ValidTransactionItem TransactionItemDTO> items;
	@NotEmpty(message = "Must not be null or empty")
	private Set<PaymentDTO> payments;

	public TransactionDTO() {
		super();
	}

	public Long getRecipientId() {
		return recipientId;
	}

	public TransactionDTO setRecipientId(Long recipientId) {
		this.recipientId = recipientId;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public TransactionDTO setDescription(String description) {
		this.description = description;
		return this;
	}

	public Double getTotal() {
		return total;
	}

	public TransactionDTO setTotal(Double total) {
		this.total = total;
		return this;
	}

	public Integer getType() {
		return type;
	}

	public TransactionDTO setType(Integer type) {
		this.type = type;
		return this;
	}
	public Double getDiscount() {
		return discount;
	}

	public TransactionDTO setDiscount(Double discount) {
		this.discount = discount;
		return this;
	}
	public Date getDate() {
		return date;
	}

	public TransactionDTO setDate(Date date) {
		this.date = date;
		return this;
	}

	public Set<Long> getCategoryIds() {
		return categoryIds;
	}

	public TransactionDTO setCategoryIds(Set<Long> categoryIds) {
		this.categoryIds = categoryIds;
		return this;
	}

	public Set<TransactionItemDTO> getItems() {
		return items;
	}

	public TransactionDTO setItems(Set<TransactionItemDTO> items) {
		this.items = items;
		return this;
	}

	public Set<PaymentDTO> getPayments() {
		return payments;
	}

	public TransactionDTO setPayments(Set<PaymentDTO> payments) {
		this.payments = payments;
		return this;
	}
	
	@Override
	public Transaction toEntity() {
		Person recipient = new Person(getRecipientId());
		Transaction trans = new Transaction(getId(), recipient, getDate(), getDescription(), getTotal(),
				getDiscount(), TransactionType.toEnum(getType()));

		for (Long catId : getCategoryIds()) {
			Category cat = new Category(catId);
			trans.addCategory(cat);
		}
		
		return trans;
	}
}
