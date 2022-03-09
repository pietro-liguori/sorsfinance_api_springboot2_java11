package com.vili.sorsfinance.entities.enums;

public enum PersonProfile {

	STANDARD(1, "Padrão"),
	HOLDER(2, "Titular"),
	BANK(3, "Banco");
	
	private int code;
	private String label;
	
	private PersonProfile(int code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static PersonProfile toEnum(int code) {
		for (PersonProfile x : PersonProfile.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid PersonProfile code");
	}
	
	public static PersonProfile toEnum(String label) {
		for (PersonProfile x : PersonProfile.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid PersonProfile label");
	}

}
