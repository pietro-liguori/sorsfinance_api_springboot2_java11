package com.vili.sorsfinance.api.domain.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import com.vili.sorsfinance.api.domain.Category;
import com.vili.sorsfinance.api.domain.CreditPayment;
import com.vili.sorsfinance.api.domain.NaturalPerson;
import com.vili.sorsfinance.api.domain.Payment;
import com.vili.sorsfinance.api.domain.Person;
import com.vili.sorsfinance.api.domain.Transaction;
import com.vili.sorsfinance.api.domain.TransactionItem;
import com.vili.sorsfinance.api.domain.enums.PaymentType;
import com.vili.sorsfinance.api.domain.enums.TransactionDirection;
import com.vili.sorsfinance.api.domain.enums.TransactionType;
import com.vili.sorsfinance.api.validation.constraints.ValidTransaction;
import com.vili.sorsfinance.api.validation.constraints.ValidTransactionItem;
import com.vili.sorsfinance.framework.DataTransferObject;

@ValidTransaction
public class TransactionDTO extends DataTransferObject {

	private Date date;
	private String description;
	private Double discount;
	private Integer type;
	private Integer direction;
	private Long recipientId;
	private Set<Long> categoryIds;
	
	@NotEmpty(message = "Must not be null or empty")
	private Set<PaymentDTO> payments;
	
	private Set<@ValidTransactionItem TransactionItemDTO> items;

	public TransactionDTO() {
		super();
	}

	public Long getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(Long recipientId) {
		this.recipientId = recipientId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Long> getCategoryIds() {
		return categoryIds.stream().toList();
	}

	public void setCategoryIds(Set<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}

	public Set<TransactionItemDTO> getItems() {
		return items;
	}

	public void setItems(Set<TransactionItemDTO> items) {
		this.items = items;
	}

	public List<PaymentDTO> getPayments() {
		return payments.stream().toList();
	}

	public void setPayments(Set<PaymentDTO> payments) {
		this.payments = payments;
	}
	
	@Override
	public Transaction toEntity() {
		Person recipient = new NaturalPerson(getRecipientId());
		Transaction trans = new Transaction(getId(), recipient, getDate(), getDescription(),
				getDiscount(), TransactionType.toEnum(getType()), TransactionDirection.toEnum(getDirection()));

		for (Long catId : getCategoryIds()) {
			Category cat = new Category(catId);
			trans.addCategory(cat);
		}
		
		for (TransactionItemDTO itemDto : getItems()) {
			TransactionItem item = itemDto.toEntity();
			trans.addItem(item);
			item.setTransaction(trans);
		}

		for (PaymentDTO payDto : getPayments()) {
			PaymentType payType = PaymentType.toEnum(payDto.getType());

			if (payType.equals(PaymentType.CREDIT)) {
				CreditPayment pay = (CreditPayment) payDto.toEntity();
				trans.addPayment(pay);
				pay.setTransaction(trans);
			} else {
				Payment pay = payDto.toEntity();
				trans.addPayment(pay);
				pay.setTransaction(trans);
			}
		}
		return trans;
	}
}
