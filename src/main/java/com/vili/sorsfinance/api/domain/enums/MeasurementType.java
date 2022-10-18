package com.vili.sorsfinance.api.domain.enums;

import com.vili.sorsfinance.framework.exceptions.custom.EnumValueNotFoundException;

public enum MeasurementType {

	LENGHT(1, "Comprimento"),
	VOLUME(2, "Volume"),
	MASS(3, "Massa"),
	TIME(4, "Tempo"),
	QUANTITY(5, "Quantidade");
	
	private Integer code;
	private String label;

	MeasurementType(Integer code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static MeasurementType toEnum(Integer code) {
		for (MeasurementType x : MeasurementType.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(code, "code", MeasurementType.class);
	}
	
	public static MeasurementType toEnum(String label) {
		for (MeasurementType x : MeasurementType.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(label, "label", MeasurementType.class);
	}
}
