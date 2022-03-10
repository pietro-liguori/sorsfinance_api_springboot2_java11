package com.vili.sorsfinance.entities.enums;

public enum PeriodUnit {

	DAY(1, "Dia"),
	WEEK(2, "Semana"),
	FORTNIGHT(3, "Quinzena"),
	MONTH(4, "Mês"),
	BIMONTH(5, "Bimestre"),
	QUARTER(6, "Trimestre"),
	SEMESTER(7, "Semestre"),
	YEAR(8, "Ano");
	
	private int code;
	private String label;
	
	private PeriodUnit(int code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static PeriodUnit toEnum(int code) {
		for (PeriodUnit x : PeriodUnit.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid PeriodUnit code");
	}
	
	public static PeriodUnit toEnum(String label) {
		for (PeriodUnit x : PeriodUnit.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid PeriodUnit label");
	}

}
