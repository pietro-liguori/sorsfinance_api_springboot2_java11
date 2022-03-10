package com.vili.sorsfinance.entities.enums;

public enum AssetType {

	SERVICE_PROVISION(1, "Prestação de serviço"),
	PRODUCT(2, "Produto");
	
	private int code;
	private String label;
	
	private AssetType(int code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static AssetType toEnum(int code) {
		for (AssetType x : AssetType.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid AssetType code");
	}
	
	public static AssetType toEnum(String label) {
		for (AssetType x : AssetType.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid AssetType label");
	}

}
