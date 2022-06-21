package com.vili.sorsfinance.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vili.sorsfinance.api.entities.enums.CardType;
import com.vili.sorsfinance.api.entities.enums.PaymentType;

public class Utils {

	public static Map<PaymentType, List<CardType>> WALLET_PAYMENT_CARDS_MAP;
	public static Map<PaymentType, List<CardType>> CHECKING_ACCOUNT_PAYMENT_CARDS_MAP;
	public static Map<PaymentType, List<CardType>> SAVINGS_ACCOUNT_PAYMENT_CARDS_MAP;
	public static Map<PaymentType, List<CardType>> SALARY_ACCOUNT_PAYMENT_CARDS_MAP;
	public static Map<PaymentType, List<CardType>> TICKET_ACCOUNT_PAYMENT_CARDS_MAP;
	
	static {
		WALLET_PAYMENT_CARDS_MAP = new HashMap<>();
		WALLET_PAYMENT_CARDS_MAP.put(PaymentType.BANK_SLIP, Arrays.asList());
		WALLET_PAYMENT_CARDS_MAP.put(PaymentType.CASH, Arrays.asList());
		WALLET_PAYMENT_CARDS_MAP.put(PaymentType.DEPOSIT, Arrays.asList());
		WALLET_PAYMENT_CARDS_MAP.put(PaymentType.INTERNAL, Arrays.asList());
		
		CHECKING_ACCOUNT_PAYMENT_CARDS_MAP = new HashMap<>();
		CHECKING_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.AUTOMATIC, Arrays.asList());
		CHECKING_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.BANK_SLIP, Arrays.asList(CardType.CREDIT, CardType.DEBIT, CardType.MULTIPLE));
		CHECKING_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.CHECK, Arrays.asList());
		CHECKING_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.CREDIT, Arrays.asList(CardType.CREDIT, CardType.MULTIPLE, CardType.ONLINE));
		CHECKING_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.DEBIT, Arrays.asList(CardType.DEBIT, CardType.MULTIPLE));
		CHECKING_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.DOC, Arrays.asList());
		CHECKING_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.PIX, Arrays.asList());
		CHECKING_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.TED, Arrays.asList());
		CHECKING_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.WITHDRAW, Arrays.asList());
		CHECKING_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.INTERNAL, Arrays.asList());

		SAVINGS_ACCOUNT_PAYMENT_CARDS_MAP = new HashMap<>();
		SAVINGS_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.AUTOMATIC, Arrays.asList());
		SAVINGS_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.DEBIT, Arrays.asList(CardType.DEBIT, CardType.MULTIPLE));
		SAVINGS_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.DOC, Arrays.asList());
		SAVINGS_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.PIX, Arrays.asList());
		SAVINGS_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.TED, Arrays.asList());
		SAVINGS_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.WITHDRAW, Arrays.asList());
		SAVINGS_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.INTERNAL, Arrays.asList());

		SALARY_ACCOUNT_PAYMENT_CARDS_MAP = new HashMap<>();
		SALARY_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.AUTOMATIC, Arrays.asList());
		SALARY_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.DEBIT, Arrays.asList(CardType.DEBIT, CardType.MULTIPLE));
		SALARY_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.DOC, Arrays.asList());
		SALARY_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.PIX, Arrays.asList());
		SALARY_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.TED, Arrays.asList());
		SALARY_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.WITHDRAW, Arrays.asList());
		SALARY_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.INTERNAL, Arrays.asList());

		TICKET_ACCOUNT_PAYMENT_CARDS_MAP = new HashMap<>();
		TICKET_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.DEBIT, Arrays.asList(CardType.FOOD_TICKET, CardType.MEAL_TICKET, CardType.FUEL_TICKET, CardType.TRANSPORT_TICKET));
		TICKET_ACCOUNT_PAYMENT_CARDS_MAP.put(PaymentType.INTERNAL, Arrays.asList());
	}
}
