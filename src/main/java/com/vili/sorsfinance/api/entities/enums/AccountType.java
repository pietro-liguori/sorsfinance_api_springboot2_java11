package com.vili.sorsfinance.api.entities.enums;

import java.util.List;
import java.util.Map;

import com.vili.sorsfinance.api.exceptions.EnumValueNotFoundException;
import com.vili.sorsfinance.utils.Utils;

public enum AccountType {

	WALLET(1, "Carteira", Utils.WALLET_PAYMENT_CARDS_MAP),
	CHECKING_ACCOUNT(2, "Conta corrente", Utils.CHECKING_ACCOUNT_PAYMENT_CARDS_MAP),
	SAVINGS_ACCOUNT(3, "Conta poupança", Utils.SAVINGS_ACCOUNT_PAYMENT_CARDS_MAP),
	SALARY_ACCOUNT(4, "Conta salário", Utils.SALARY_ACCOUNT_PAYMENT_CARDS_MAP),
	TICKET_ACCOUNT(5, "Ticket", Utils.TICKET_ACCOUNT_PAYMENT_CARDS_MAP);

	private Integer code;
	private String label;
	Map<PaymentType, List<CardType>> paymentCardsMap;

	private AccountType(Integer code, String label, Map<PaymentType, List<CardType>> paymentCardsMap) {
		this.code = code;
		this.label = label;
		this.paymentCardsMap = paymentCardsMap;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public Map<PaymentType, List<CardType>> getPaymentCardsMap() {
		return paymentCardsMap;
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
