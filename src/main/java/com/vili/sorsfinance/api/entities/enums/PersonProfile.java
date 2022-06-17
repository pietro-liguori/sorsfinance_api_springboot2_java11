package com.vili.sorsfinance.api.entities.enums;

import com.vili.sorsfinance.api.exceptions.EnumValueNotFoundException;

public enum PersonProfile {

	STANDARD(1, "Padrão"),
	HOLDER(2, "Titular"),
	BANK(3, "Banco");
	
	private Integer code;
	private String label;
	
	private PersonProfile(Integer code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static PersonProfile toEnum(Integer code) {
		for (PersonProfile x : PersonProfile.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(code, "code", PersonProfile.class);
	}
	
	public static PersonProfile toEnum(String label) {
		for (PersonProfile x : PersonProfile.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(label, "label", PersonProfile.class);
	}

}
