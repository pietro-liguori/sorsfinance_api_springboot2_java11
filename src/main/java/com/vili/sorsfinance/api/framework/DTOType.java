package com.vili.sorsfinance.api.framework;

import com.vili.sorsfinance.api.exceptions.EnumValueNotFoundException;

public enum DTOType {

	INSERT(1, "insert"),
	UPDATE(2, "update");
	
	private int code;
	private String label;
	
	private DTOType(int code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static DTOType toEnum(int code) {
		for (DTOType x : DTOType.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(code, "code", DTOType.class);
	}
	
	public static DTOType toEnum(String label) {
		for (DTOType x : DTOType.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(label, "label", DTOType.class);
	}
}
