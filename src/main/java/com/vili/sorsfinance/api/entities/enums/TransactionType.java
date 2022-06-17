package com.vili.sorsfinance.api.entities.enums;

import com.vili.sorsfinance.api.exceptions.EnumValueNotFoundException;

public enum TransactionType {

	DEFAULT(1, "Padrão"),
	WITHDRAW(2, "Saque em dinheiro"),
	BETWEEN_HOLDERS(3, "Transferência entre titulares");
	
	private Integer code;
	private String label;
	
	private TransactionType(Integer code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
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
