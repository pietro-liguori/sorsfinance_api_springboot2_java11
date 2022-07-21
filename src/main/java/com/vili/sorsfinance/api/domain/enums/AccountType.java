package com.vili.sorsfinance.api.domain.enums;

import java.util.List;
import java.util.Map;

import com.vili.sorsfinance.api.utils.TypeAssociations;
import com.vili.sorsfinance.framework.exceptions.EnumValueNotFoundException;

public enum AccountType {

	WALLET(1, "Carteira", TypeAssociations.WALLET_PAYMENT_CARDS_MAP),
	CHECKING_ACCOUNT(2, "Conta corrente", TypeAssociations.CHECKING_ACCOUNT_PAYMENT_CARDS_MAP),
	SAVINGS_ACCOUNT(3, "Conta poupança", TypeAssociations.SAVINGS_ACCOUNT_PAYMENT_CARDS_MAP),
	SALARY_ACCOUNT(4, "Conta salário", TypeAssociations.SALARY_ACCOUNT_PAYMENT_CARDS_MAP),
	TICKET_ACCOUNT(5, "Ticket", TypeAssociations.TICKET_ACCOUNT_PAYMENT_CARDS_MAP);

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
