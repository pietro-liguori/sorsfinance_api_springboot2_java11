package com.vili.sorsfinance.entities.enums;

public enum CardType {

	DEBIT(1, "Débito"),
	CREDIT(2, "Crédito"),
	MULTIPLE(3, "Múltiplo"),
	ONLINE(4, "Online"),
	VOUCHER(5, "Voucher");
	
	private int code;
	private String label;
	
	private CardType(int code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static CardType toEnum(int code) {
		for (CardType x : CardType.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid CardType code");
	}
	
	public static CardType toEnum(String label) {
		for (CardType x : CardType.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid CardType label");
	}

}
