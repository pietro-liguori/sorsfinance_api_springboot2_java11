package com.vili.sorsfinance.api.domain.enums;

import com.vili.sorsfinance.framework.exceptions.custom.EnumValueNotFoundException;

public enum AddressType {

	RESIDENTIAL(1, "Residencial"),
	BUSINESS(2, "Comercial"),
	OTHER(3, "Outro");
	
	private Integer code;
	private String label;
	
	private AddressType(Integer code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static AddressType toEnum(Integer code) {
		for (AddressType x : AddressType.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(code, "code", AddressType.class);
	}
	
	public static AddressType toEnum(String label) {
		for (AddressType x : AddressType.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(label, "label", AddressType.class);
	}

}
