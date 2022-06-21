package com.vili.sorsfinance.api.entities.enums;

import com.vili.sorsfinance.api.exceptions.EnumValueNotFoundException;

public enum AssetType {

	SERVICE_PROVISION(1, "Prestação de serviço"),
	PRODUCT(2, "Produto");
	
	private Integer code;
	private String label;
	
	private AssetType(Integer code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static AssetType toEnum(Integer code) {
		for (AssetType x : AssetType.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(code, "code", AssetType.class);
	}
	
	public static AssetType toEnum(String label) {
		for (AssetType x : AssetType.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		throw new EnumValueNotFoundException(label, "label", AssetType.class);
	}
}
