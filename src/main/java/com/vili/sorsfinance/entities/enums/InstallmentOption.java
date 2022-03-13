package com.vili.sorsfinance.entities.enums;

public enum InstallmentOption {

	DIFFERENCE_ON_FIRST(1, "Diferença ao final"),
	DIFFERENCE_ON_LAST(2, "Diferença no início");
	
	private int code;
	private String label;
	
	private InstallmentOption(int code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static InstallmentOption toEnum(int code) {
		for (InstallmentOption x : InstallmentOption.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid InstallmentOption code");
	}
	
	public static InstallmentOption toEnum(String label) {
		for (InstallmentOption x : InstallmentOption.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid InstallmentOption label");
	}

}
