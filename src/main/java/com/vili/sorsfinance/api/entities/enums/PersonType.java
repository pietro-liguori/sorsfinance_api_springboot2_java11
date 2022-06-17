package com.vili.sorsfinance.api.entities.enums;

import com.vili.sorsfinance.api.exceptions.EnumValueNotFoundException;

public enum PersonType {

	NATURAL_PERSON(1, "Pessoa Física"),
	LEGAL_PERSON(2, "Pessoa Jurídica");
	
	private Integer code;
	private String label;
	
	private PersonType(Integer code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static PersonType toEnum(Integer code) {
		for (PersonType x : PersonType.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(code, "code", PersonType.class);
	}
	
	public static PersonType toEnum(String label) {
		for (PersonType x : PersonType.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(label, "label", PersonType.class);
	}

}
