package com.vili.sorsfinance.api.domain.enums;

import java.util.List;

import com.vili.sorsfinance.api.utils.TypeAssociations;
import com.vili.sorsfinance.framework.exceptions.EnumValueNotFoundException;

public enum TransactionType {

	DEFAULT(1, "Padrão", TypeAssociations.DEFAULT_TRANSACTION_PAYMENT_TYPE_LIST),
	WITHDRAW(2, "Saque em dinheiro", TypeAssociations.WITHDRAW_PAYMENT_TYPE_LIST),
	BETWEEN_HOLDERS(3, "Transferência entre titulares", TypeAssociations.BETWEEN_HOLDERS_PAYMENT_TYPE_LIST),
	CREDITCARD_STATEMENT_PAYMENT(4, "Pagamento de fatura do cartão de crédito", TypeAssociations.CREDITCARD_STATEMENT_PAYMENT_PAYMENT_TYPE_LIST);
	
	private Integer code;
	private String label;
	private List<PaymentType> paymentTypes;
	
	private TransactionType(Integer code, String label, List<PaymentType> paymentTypes) {
		this.code = code;
		this.label = label;
		this.paymentTypes = paymentTypes;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public List<PaymentType> getPaymentTypes() {
		return paymentTypes;
	}

	public static TransactionType toEnum(Integer code) {
		for (TransactionType x : TransactionType.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(code, "code", TransactionType.class);
	}
	
	public static TransactionType toEnum(String label) {
		for (TransactionType x : TransactionType.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(label, "label", TransactionType.class);
	}

}
