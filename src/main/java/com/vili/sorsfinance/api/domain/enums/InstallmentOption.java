package com.vili.sorsfinance.api.domain.enums;

import com.vili.sorsfinance.framework.exceptions.custom.EnumValueNotFoundException;

public enum InstallmentOption {

	DIFFERENCE_ON_FIRST(1, "Diferença ao final"),
	DIFFERENCE_ON_LAST(2, "Diferença no início");
	
	private Integer code;
	private String label;
	
	private InstallmentOption(Integer code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static InstallmentOption toEnum(Integer code) {
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
