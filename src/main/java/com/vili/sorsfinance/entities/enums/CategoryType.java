package com.vili.sorsfinance.entities.enums;

public enum CategoryType {

	ALL(1, "Todos"),
	ASSET(2, "Ativo"),
	TRANSACTION(3, "Transação");
	
	private int code;
	private String label;
	
	private CategoryType(int code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static CategoryType toEnum(int code) {
		for (CategoryType x : CategoryType.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid CategoryType code");
	}
	
	public static CategoryType toEnum(String label) {
		for (CategoryType x : CategoryType.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid CategoryType label");
	}

}
