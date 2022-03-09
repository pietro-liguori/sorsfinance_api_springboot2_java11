package com.vili.sorsfinance.entities.enums;

public enum PersonType {

	NATURAL_PERSON(1, "Pessoa Física"),
	LEGAL_PERSON(2, "Pessoa Jurídica");
	
	private int code;
	private String label;
	
	private PersonType(int code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static PersonType toEnum(int code) {
		for (PersonType x : PersonType.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid PersonType code");
	}
	
	public static PersonType toEnum(String label) {
		for (PersonType x : PersonType.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid PersonType label");
	}

}
