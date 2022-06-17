package com.vili.sorsfinance.api.entities.enums;

import com.vili.sorsfinance.api.exceptions.EnumValueNotFoundException;

public enum AccountType {

	WALLET(1, "Carteira"),
	CHECKING_ACCOUNT(2, "Conta corrente"),
	SAVINGS_ACCOUNT(3, "Conta poupança"),
	SALARY_ACCOUNT(4, "Conta salário"),
	TICKET_ACCOUNT(5, "Ticket");

	private Integer code;
	private String label;
	
	private AccountType(Integer code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static AccountType toEnum(Integer code) {

		for (AccountType x : AccountType.values()) {
			if (x.getCode() == code) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(code, "code", AccountType.class);
	}
	
	public static AccountType toEnum(String label) {

		for (AccountType x : AccountType.values()) {
			if (x.getLabel() == label) {
				return x;
			}
		}
		
		throw new EnumValueNotFoundException(label, "label", AccountType.class);
	}

}
