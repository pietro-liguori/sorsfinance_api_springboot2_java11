package com.vili.sorsfinance.api.entities.enums;

import com.vili.sorsfinance.api.exceptions.EnumValueNotFoundException;

public enum CardType {

	DEBIT(1, "Débito"),
	CREDIT(2, "Crédito"),
	MULTIPLE(3, "Múltiplo"),
	ONLINE(4, "Online"),
	MEAL_TICKET(5, "Voucher"),
	FOOD_TICKET(6, "Voucher"),
	TRANSPORT_TICKET(7, "Voucher"),
	FUEL_TICKET(8, "Voucher");
	
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
