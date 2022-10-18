package com.vili.sorsfinance.api.domain.enums;

import com.vili.sorsfinance.framework.exceptions.custom.EnumValueNotFoundException;

public enum CadastralStatus {

	ACTIVE(1, "Ativo"),
	INACTIVE(2, "Inativo");
	
	private Integer code;
	private String label;
	
	private CadastralStatus(Integer code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static CadastralStatus toEnum(Integer code) {
		for (CadastralStatus x : CadastralStatus.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(code, "code", CadastralStatus.class);
	}
	
	public static CadastralStatus toEnum(String label) {
		for (CadastralStatus x : CadastralStatus.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(label, "label", CadastralStatus.class);
	}
}
