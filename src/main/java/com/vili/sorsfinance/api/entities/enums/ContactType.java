package com.vili.sorsfinance.api.entities.enums;

import com.vili.sorsfinance.api.exceptions.EnumValueNotFoundException;

public enum ContactType {

	MAILING_ADDRESS(1, "Endereço"),
	EMAIL(2, "E-mail"),
	PHONE(3, "Telefone");
	
	private Integer code;
	private String label;
	
	private ContactType(Integer code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static ContactType toEnum(Integer code) {
		for (ContactType x : ContactType.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(code, "code", ContactType.class);
	}
	
	public static ContactType toEnum(String label) {
		for (ContactType x : ContactType.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(label, "label", ContactType.class);
	}

}
