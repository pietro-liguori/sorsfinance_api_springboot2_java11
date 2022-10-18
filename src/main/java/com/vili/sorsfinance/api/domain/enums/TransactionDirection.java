package com.vili.sorsfinance.api.domain.enums;

import com.vili.sorsfinance.framework.exceptions.custom.EnumValueNotFoundException;

public enum TransactionDirection {

	INPUT(1, "Receita"),
	OUTPUT(2, "Despesa");
	
	private Integer code;
	private String label;
	
	private TransactionDirection(Integer code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static TransactionDirection toEnum(Integer code) {
		for (TransactionDirection x : TransactionDirection.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(code, "code", TransactionDirection.class);
	}
	
	public static TransactionDirection toEnum(String label) {
		for (TransactionDirection x : TransactionDirection.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(label, "label", TransactionDirection.class);
	}
}
