package com.vili.sorsfinance.api.domain.enums;

import com.vili.sorsfinance.framework.exceptions.EnumValueNotFoundException;

public enum CardStatus {

	BLOCKED(1, "Bloqueado"),
	UNBLOCKED(2, "Desbloqueado"),
	CANCELED(3, "Cancelado");
	
	private Integer code;
	private String label;
	
	private CardStatus(Integer code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static CardStatus toEnum(Integer code) {
		for (CardStatus x : CardStatus.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(code, "code", CardStatus.class);
	}
	
	public static CardStatus toEnum(String label) {
		for (CardStatus x : CardStatus.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(label, "label", CardStatus.class);
	}
}
