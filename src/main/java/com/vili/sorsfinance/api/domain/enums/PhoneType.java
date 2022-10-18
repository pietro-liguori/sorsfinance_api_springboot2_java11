package com.vili.sorsfinance.api.domain.enums;

import com.vili.sorsfinance.framework.exceptions.custom.EnumValueNotFoundException;

public enum PhoneType {

	RESIDENTIAL(1, "Residencial"),
	MOBILE(2, "Celular"),
	BUSINESS(3, "Comercial"),
	OTHER(4, "Outro");
	
	private Integer code;
	private String label;
	
	private PhoneType(Integer code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static PhoneType toEnum(Integer code) {
		for (PhoneType x : PhoneType.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(code, "code", PhoneType.class);
	}
	
	public static PhoneType toEnum(String label) {
		for (PhoneType x : PhoneType.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(label, "label", PhoneType.class);
	}

}
