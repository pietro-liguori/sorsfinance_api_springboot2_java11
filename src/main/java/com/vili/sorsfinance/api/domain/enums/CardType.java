package com.vili.sorsfinance.api.domain.enums;

import com.vili.sorsfinance.framework.exceptions.custom.EnumValueNotFoundException;

public enum CardType {

	DEBIT(1, "Débito"),
	CREDIT(2, "Crédito"),
	MULTIPLE(3, "Múltiplo"),
	ONLINE(4, "Online"),
	MEAL_TICKET(5, "Vale Refeição"),
	FOOD_TICKET(6, "Vale Alimentação"),
	TRANSPORT_TICKET(7, "Vale Transporte"),
	FUEL_TICKET(8, "Vale Combustível");
	
	private Integer code;
	private String label;
	
	private CardType(Integer code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static CardType toEnum(Integer code) {
		for (CardType x : CardType.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(code, "code", CardType.class);
	}
	
	public static CardType toEnum(String label) {
		for (CardType x : CardType.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(label, "label", CardType.class);
	}
}