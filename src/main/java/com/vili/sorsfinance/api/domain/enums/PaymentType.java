package com.vili.sorsfinance.api.domain.enums;

import com.vili.sorsfinance.framework.exceptions.custom.EnumValueNotFoundException;

public enum PaymentType {

	INTERNAL(1, "Interno"),
	WITHDRAW(2, "Saque"),
	CREDIT(3, "Crédito"),
	BANK_SLIP(4, "Boleto"),
	CASH(5, "Dinheiro"),
	DEBIT(6, "Débito"),
	AUTOMATIC(7, "Automático"),
	CHECK(8, "Cheque"),
	DEPOSIT(9, "Depósito"),
	DOC(10, "DOC"),
	TED(11, "TED"),
	PIX(12, "PIX"),
	VOUCHER(13, "Voucher");
	
	private Integer code;
	private String label;

	private PaymentType(Integer code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}

	public static PaymentType toEnum(Integer code) {
		for (PaymentType x : PaymentType.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(code, "code", PaymentType.class);
	}
	
	public static PaymentType toEnum(String label) {
		for (PaymentType x : PaymentType.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(label, "label", PaymentType.class);
	}
}
