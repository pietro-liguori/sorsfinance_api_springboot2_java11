package com.vili.sorsfinance.api.domain.enums;

import java.util.List;
import java.util.Map;

import com.vili.sorsfinance.api.utils.TypeAssociations;
import com.vili.sorsfinance.framework.exceptions.custom.EnumValueNotFoundException;

public enum AccountType {

	WALLET(1, "Carteira", TypeAssociations.ENABLED_CARDS_FOR_WALLETS),
	CHECKING_ACCOUNT(2, "Conta corrente", TypeAssociations.ENABLED_CARDS_FOR_CHECKING_ACCOUNTS),
	SAVINGS_ACCOUNT(3, "Conta poupança", TypeAssociations.ENABLED_CARDS_FOR_SAVINGS_ACCOUNTS),
	SALARY_ACCOUNT(4, "Conta salário", TypeAssociations.ENABLED_CARDS_FOR_SALARY_ACCOUNTS),
	VOUCHER_ACCOUNT(5, "Ticket", TypeAssociations.ENABLED_CARDS_FOR_PROVIDER_ACCOUNTS);

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
	
	public Map<PaymentType, List<CardType>> getEnabledCardsByPaymentType() {
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
