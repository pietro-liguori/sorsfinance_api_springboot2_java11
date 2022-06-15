package com.vili.sorsfinance.api.entities.enums;

import com.vili.sorsfinance.api.exceptions.EnumValueNotFoundException;

public enum CardStatus {

	BLOCKED(1, "Bloqueado"),
	UNBLOCKED(2, "Desbloqueado"),
	CANCELED(3, "Cancelado");
	
	private int code;
	private String label;
	
	private CardStatus(int code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static CardStatus toEnum(int code) {
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
