package com.vili.sorsfinance.api.entities.enums;

import com.vili.sorsfinance.api.exceptions.EnumValueNotFoundException;

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
		
		throw new EnumValueNotFoundException(code, "code", InstallmentOption.class);
	}
	
	public static InstallmentOption toEnum(String label) {
		for (InstallmentOption x : InstallmentOption.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(label, "label", InstallmentOption.class);
	}

}
