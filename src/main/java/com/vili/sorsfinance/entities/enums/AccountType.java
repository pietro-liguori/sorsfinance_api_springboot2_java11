package com.vili.sorsfinance.entities.enums;

public enum AccountType {

	WALLET(1, "Carteira"),
	CHECKING_ACCOUNT(2, "Conta corrente"),
	SAVINGS_ACCOUNT(3, "Conta poupança"),
	SALARY_ACCOUNT(4, "Conta salário"),
	MEAL_TICKET(5, "Vale refeição"),
	FOOD_TICKET(6, "Vale alimentação"),
	FUEL_TICKET(7, "Vale combustível");
	
	private int code;
	private String label;
	
	private AccountType(int code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static AccountType toEnum(int code) {
		for (AccountType x : AccountType.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid AccountType code");
	}
	
	public static AccountType toEnum(String label) {
		for (AccountType x : AccountType.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid AccountType label");
	}

}
