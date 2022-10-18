package com.vili.sorsfinance.api.domain.enums;

import java.util.List;

import com.vili.sorsfinance.api.utils.TypeAssociations;
import com.vili.sorsfinance.framework.exceptions.custom.EnumValueNotFoundException;

public enum TransactionType {

	DEFAULT(1, "Padrão", TypeAssociations.ENABLED_PAYMENTS_FOR_DEFAULT_TRANSACTION),
	WITHDRAW(2, "Saque em dinheiro", TypeAssociations.ENABLED_PAYMENTS_FOR_WITHDRAW),
	TRANSFER_BETWEEN_HOLDERS(3, "Transferência entre titulares", TypeAssociations.ENABLED_PAYMENTS_FOR_TRANSFER_BETWEEN_HOLDERS),
	CREDITCARD_STATEMENT_PAYMENT(4, "Pagamento de fatura do cartão de crédito", TypeAssociations.ENABLED_PAYMENTS_FOR_CREDITCARD_STATEMENT_PAYMENT),
	BANK_SLIP_PAYMENT(5, "Pagamento de boleto", TypeAssociations.ENABLED_PAYMENTS_FOR_BANK_SLIP_PAYMENT),
	CHECK_PAYMENT(6, "Cheque descontado", TypeAssociations.ENABLED_PAYMENTS_FOR_CHECK_PAYMENT),
	INTERNAL(7, "Interna", TypeAssociations.ENABLED_PAYMENTS_FOR_INTERNAL_TRANSACTION);

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
