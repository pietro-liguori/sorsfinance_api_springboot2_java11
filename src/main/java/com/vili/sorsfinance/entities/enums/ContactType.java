package com.vili.sorsfinance.entities.enums;

public enum ContactType {

	MAILING_ADDRESS(1, "Endereço"),
	EMAIL(2, "E-mail"),
	PHONE(3, "Telefone");
	
	private int code;
	private String label;
	
	private ContactType(int code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static ContactType toEnum(int code) {
		for (ContactType x : ContactType.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ContactType code");
	}
	
	public static ContactType toEnum(String label) {
		for (ContactType x : ContactType.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ContactType label");
	}

}
