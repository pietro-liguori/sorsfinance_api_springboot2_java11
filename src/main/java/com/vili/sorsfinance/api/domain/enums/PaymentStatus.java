package com.vili.sorsfinance.api.domain.enums;

import com.vili.sorsfinance.framework.exceptions.EnumValueNotFoundException;

public enum PaymentStatus {

	PAID(1, "Pago"),
	NOT_PAID(2, "Não pago"),
	UNDER_PAID(3, "Pago a menor"),
	OVER_PAID(4, "Pago a maior"),
	EXPIRED(5, "Vencido"),
	CANCELED(6, "Cancelado");
	
	private Integer code;
	private String label;
	
	private PaymentStatus(Integer code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static PaymentStatus toEnum(Integer code) {
		for (PaymentStatus x : PaymentStatus.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(code, "code", PaymentStatus.class);
	}
	
	public static PaymentStatus toEnum(String label) {
		for (PaymentStatus x : PaymentStatus.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(label, "label", PaymentStatus.class);
	}

}
