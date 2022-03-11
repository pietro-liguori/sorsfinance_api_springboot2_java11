package com.vili.sorsfinance.entities.enums;

public enum PaymentStatus {

	PAID(1, "Pago"),
	NOT_PAID(2, "Não pago"),
	UNDER_PAID(3, "Pago a menor"),
	OVER_PAID(4, "Pago a maior"),
	EXPIRED(5, "Vencido"),
	CANCELED(6, "Cancelado");
	
	private int code;
	private String label;
	
	private PaymentStatus(int code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static PaymentStatus toEnum(int code) {
		for (PaymentStatus x : PaymentStatus.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid PaymentStatus code");
	}
	
	public static PaymentStatus toEnum(String label) {
		for (PaymentStatus x : PaymentStatus.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid PaymentStatus label");
	}

}
