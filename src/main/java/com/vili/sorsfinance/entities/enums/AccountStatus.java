package com.vili.sorsfinance.entities.enums;

public enum AccountStatus {

	ACTIVE(1, "Ativa"),
	CLOSED(2, "Fechada"),
	BLOCKED(3, "Bloqueada");
	
	private int code;
	private String label;
	
	private AccountStatus(int code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static AccountStatus toEnum(int code) {
		for (AccountStatus x : AccountStatus.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid AccountStatus code");
	}
	
	public static AccountStatus toEnum(String label) {
		for (AccountStatus x : AccountStatus.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid AccountStatus label");
	}

}
