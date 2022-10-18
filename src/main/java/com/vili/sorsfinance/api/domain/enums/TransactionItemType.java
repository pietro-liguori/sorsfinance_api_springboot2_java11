package com.vili.sorsfinance.api.domain.enums;

import com.vili.sorsfinance.framework.exceptions.custom.EnumValueNotFoundException;

public enum TransactionItemType {

	SERVICE_PROVISION_ITEM(1, "Prestação de Serviço"),
	PRODUCT_ITEM(2, "Produto");

	private Integer code;
	private String label;
	
	private TransactionItemType(Integer code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}

	public static TransactionItemType toEnum(Integer code) {
		for (TransactionItemType x : TransactionItemType.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(code, "code", TransactionItemType.class);
	}
	
	public static TransactionItemType toEnum(String label) {
		for (TransactionItemType x : TransactionItemType.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(label, "label", TransactionItemType.class);
	}

}
