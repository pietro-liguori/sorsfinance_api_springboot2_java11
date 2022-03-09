package com.vili.sorsfinance.entities.enums;

public enum PhoneType {

	RESIDENTIAL(1, "Residencial"),
	MOBILE(2, "Celular"),
	BUSINESS(3, "Comercial"),
	OTHER(4, "Outro");
	
	private int code;
	private String label;
	
	private PhoneType(int code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static PhoneType toEnum(int code) {
		for (PhoneType x : PhoneType.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid PhoneType code");
	}
	
	public static PhoneType toEnum(String label) {
		for (PhoneType x : PhoneType.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid PhoneType label");
	}

}
