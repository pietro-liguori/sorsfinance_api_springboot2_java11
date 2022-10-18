package com.vili.sorsfinance.api.domain.enums;

import com.vili.sorsfinance.framework.exceptions.custom.EnumValueNotFoundException;

public enum MeasurementUnit {

	METER(1, "Metro", "m", MeasurementType.LENGHT),
	KILOMETER(2, "Kilometro", "km", MeasurementType.LENGHT),
	MILLIMETER(3, "Milímetro", "mm", MeasurementType.LENGHT),
	INCH(4, "Polegada", "\"", MeasurementType.LENGHT),
	FOOT(5, "Pé", "pé", MeasurementType.LENGHT),
	MILE(6, "Milha", "mi", MeasurementType.LENGHT),
	YARD(7, "Jarda", "yd", MeasurementType.LENGHT),
	GRAM(8, "Grama", "g", MeasurementType.MASS),
	KILOGRAM(9, "Kilograma", "kg", MeasurementType.MASS),
	MILLIGRAM(10, "Miligrama", "mg", MeasurementType.MASS),
	TON(11, "Tonelada", "ton", MeasurementType.MASS),
	LITER(12, "Litro", "l", MeasurementType.VOLUME),
	MILLILITER(13, "Mililitro", "ml", MeasurementType.VOLUME),
	CUBIC_METER(14, "Metro Cúbico", "m³", MeasurementType.VOLUME),
	OUNCE(15, "Onça", "oz", MeasurementType.VOLUME),
	UNIT(16, "Unidade", "un.", MeasurementType.QUANTITY),
	BOX(17, "Caixa", "cx", MeasurementType.QUANTITY),
	PACK(18, "Pacote", "pct", MeasurementType.QUANTITY);

	private Integer code;
	private String label;
	private String symbol;
	private MeasurementType type;

	MeasurementUnit(Integer code, String label, String symbol, MeasurementType type) {
		this.code = code;
		this.label = label;
		this.symbol = symbol;
		this.type = type;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getSymbol() {
		return symbol;
	}

	public MeasurementType getType() {
		return type;
	}

	public static MeasurementUnit toEnum(Integer code) {
		for (MeasurementUnit x : MeasurementUnit.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(code, "code", MeasurementUnit.class);
	}
	
	public static MeasurementUnit toEnum(String label) {
		for (MeasurementUnit x : MeasurementUnit.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(label, "label", MeasurementUnit.class);
	}
}
