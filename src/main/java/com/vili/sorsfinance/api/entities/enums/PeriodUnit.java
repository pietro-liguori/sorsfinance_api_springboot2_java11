package com.vili.sorsfinance.api.entities.enums;

import com.vili.sorsfinance.api.exceptions.EnumValueNotFoundException;

public enum PeriodUnit {

	DAY(1, "Dia"),
	WEEK(2, "Semana"),
	FORTNIGHT(3, "Quinzena"),
	MONTH(4, "Mês"),
	BIMONTH(5, "Bimestre"),
	QUARTER(6, "Trimestre"),
	SEMESTER(7, "Semestre"),
	YEAR(8, "Ano");
	
	private Integer code;
	private String label;
	
	private PeriodUnit(Integer code, String label) {
		this.code = code;
		this.label = label;
	}

	private PeriodUnit(Integer code) {
		this.code = code;
	}

	private PeriodUnit() {
	}

	public Integer getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static PeriodUnit toEnum(Integer code) {
		for (PeriodUnit x : PeriodUnit.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(code, "code", PeriodUnit.class);
	}
	
	public static PeriodUnit toEnum(String label) {
		for (PeriodUnit x : PeriodUnit.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(label, "label", PeriodUnit.class);
	}

}
