package com.vili.sorsfinance.api.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vili.sorsfinance.api.domain.enums.CardType;
import com.vili.sorsfinance.api.domain.enums.PaymentType;

public final class TypeAssociations {

	// Enabled card types for account types, by payment type
	public static Map<PaymentType, List<CardType>> ENABLED_CARDS_FOR_WALLETS;
	public static Map<PaymentType, List<CardType>> ENABLED_CARDS_FOR_CHECKING_ACCOUNTS;
	public static Map<PaymentType, List<CardType>> ENABLED_CARDS_FOR_SAVINGS_ACCOUNTS;
	public static Map<PaymentType, List<CardType>> ENABLED_CARDS_FOR_SALARY_ACCOUNTS;
	public static Map<PaymentType, List<CardType>> ENABLED_CARDS_FOR_PROVIDER_ACCOUNTS;
	
	// Enabled payment types for transaction types
	public static List<PaymentType> ENABLED_PAYMENTS_FOR_DEFAULT_TRANSACTION;
	public static List<PaymentType> ENABLED_PAYMENTS_FOR_CREDITCARD_STATEMENT_PAYMENT;
	public static List<PaymentType> ENABLED_PAYMENTS_FOR_TRANSFER_BETWEEN_HOLDERS;
	public static List<PaymentType> ENABLED_PAYMENTS_FOR_WITHDRAW;
	public static List<PaymentType> ENABLED_PAYMENTS_FOR_BANK_SLIP_PAYMENT;
	public static List<PaymentType> ENABLED_PAYMENTS_FOR_CHECK_PAYMENT;
	public static List<PaymentType> ENABLED_PAYMENTS_FOR_INTERNAL_TRANSACTION;

	static {
		buildEnabledCardsForCheckingAccounts();
		buildEnabledCardsForSalaryAccounts();
		buildEnabledCardsForSavingsAccounts();
		buildEnabledCardsForProviderAccounts();
		buildEnabledCardsForWallets();
		buildEnabledPaymentsForBankSlipPayment();
		buildEnabledPaymentsForCheckPayment();
		buildEnabledPaymentsForCreditCardStatementPayment();
		buildEnabledPaymentsForDefaultTransaction();
		buildEnabledPaymentsForInternalTransaction();
		buildEnabledPaymentsForTransferBetweenHolders();
		buildEnabledPaymentsForWithdraw();
	}
	
	private static void buildEnabledCardsForCheckingAccounts() {
		ENABLED_CARDS_FOR_CHECKING_ACCOUNTS = new HashMap<>();
		ENABLED_CARDS_FOR_CHECKING_ACCOUNTS.put(PaymentType.AUTOMATIC, Arrays.asList());
		ENABLED_CARDS_FOR_CHECKING_ACCOUNTS.put(PaymentType.BANK_SLIP, Arrays.asList(CardType.CREDIT, CardType.DEBIT, CardType.MULTIPLE));
		ENABLED_CARDS_FOR_CHECKING_ACCOUNTS.put(PaymentType.CHECK, Arrays.asList());
		ENABLED_CARDS_FOR_CHECKING_ACCOUNTS.put(PaymentType.CREDIT, Arrays.asList(CardType.CREDIT, CardType.MULTIPLE, CardType.ONLINE));
		ENABLED_CARDS_FOR_CHECKING_ACCOUNTS.put(PaymentType.DEBIT, Arrays.asList(CardType.DEBIT, CardType.MULTIPLE));
		ENABLED_CARDS_FOR_CHECKING_ACCOUNTS.put(PaymentType.DOC, Arrays.asList());
		ENABLED_CARDS_FOR_CHECKING_ACCOUNTS.put(PaymentType.PIX, Arrays.asList());
		ENABLED_CARDS_FOR_CHECKING_ACCOUNTS.put(PaymentType.TED, Arrays.asList());
		ENABLED_CARDS_FOR_CHECKING_ACCOUNTS.put(PaymentType.WITHDRAW, Arrays.asList());
		ENABLED_CARDS_FOR_CHECKING_ACCOUNTS.put(PaymentType.INTERNAL, Arrays.asList());
	}

	private static void buildEnabledCardsForSalaryAccounts() {
		ENABLED_CARDS_FOR_SALARY_ACCOUNTS = new HashMap<>();
		ENABLED_CARDS_FOR_SALARY_ACCOUNTS.put(PaymentType.AUTOMATIC, Arrays.asList());
		ENABLED_CARDS_FOR_SALARY_ACCOUNTS.put(PaymentType.DEBIT, Arrays.asList(CardType.DEBIT, CardType.MULTIPLE));
		ENABLED_CARDS_FOR_SALARY_ACCOUNTS.put(PaymentType.DOC, Arrays.asList());
		ENABLED_CARDS_FOR_SALARY_ACCOUNTS.put(PaymentType.PIX, Arrays.asList());
		ENABLED_CARDS_FOR_SALARY_ACCOUNTS.put(PaymentType.TED, Arrays.asList());
		ENABLED_CARDS_FOR_SALARY_ACCOUNTS.put(PaymentType.WITHDRAW, Arrays.asList());
		ENABLED_CARDS_FOR_SALARY_ACCOUNTS.put(PaymentType.INTERNAL, Arrays.asList());
	}
	
	private static void buildEnabledCardsForSavingsAccounts() {
		ENABLED_CARDS_FOR_SAVINGS_ACCOUNTS = new HashMap<>();
		ENABLED_CARDS_FOR_SAVINGS_ACCOUNTS.put(PaymentType.AUTOMATIC, Arrays.asList());
		ENABLED_CARDS_FOR_SAVINGS_ACCOUNTS.put(PaymentType.DEBIT, Arrays.asList(CardType.DEBIT, CardType.MULTIPLE));
		ENABLED_CARDS_FOR_SAVINGS_ACCOUNTS.put(PaymentType.DOC, Arrays.asList());
		ENABLED_CARDS_FOR_SAVINGS_ACCOUNTS.put(PaymentType.PIX, Arrays.asList());
		ENABLED_CARDS_FOR_SAVINGS_ACCOUNTS.put(PaymentType.TED, Arrays.asList());
		ENABLED_CARDS_FOR_SAVINGS_ACCOUNTS.put(PaymentType.WITHDRAW, Arrays.asList());
		ENABLED_CARDS_FOR_SAVINGS_ACCOUNTS.put(PaymentType.INTERNAL, Arrays.asList());
	}

	private static void buildEnabledCardsForProviderAccounts() {
		ENABLED_CARDS_FOR_PROVIDER_ACCOUNTS = new HashMap<>();
		ENABLED_CARDS_FOR_PROVIDER_ACCOUNTS.put(PaymentType.VOUCHER, Arrays.asList(CardType.FOOD_TICKET, CardType.MEAL_TICKET, CardType.FUEL_TICKET, CardType.TRANSPORT_TICKET));
		ENABLED_CARDS_FOR_PROVIDER_ACCOUNTS.put(PaymentType.INTERNAL, Arrays.asList());
	}

	private static void buildEnabledCardsForWallets() {
		ENABLED_CARDS_FOR_WALLETS = new HashMap<>();
		ENABLED_CARDS_FOR_WALLETS.put(PaymentType.BANK_SLIP, Arrays.asList());
		ENABLED_CARDS_FOR_WALLETS.put(PaymentType.CASH, Arrays.asList());
		ENABLED_CARDS_FOR_WALLETS.put(PaymentType.DEPOSIT, Arrays.asList());
		ENABLED_CARDS_FOR_WALLETS.put(PaymentType.INTERNAL, Arrays.asList());
	}
	
	private static void buildEnabledPaymentsForBankSlipPayment() {
		ENABLED_PAYMENTS_FOR_BANK_SLIP_PAYMENT = new ArrayList<>();
		ENABLED_PAYMENTS_FOR_BANK_SLIP_PAYMENT.addAll(Arrays.asList(PaymentType.AUTOMATIC, PaymentType.BANK_SLIP, PaymentType.INTERNAL, PaymentType.DEBIT, PaymentType.CASH, PaymentType.CREDIT, PaymentType.PIX));
	}

	private static void buildEnabledPaymentsForCheckPayment() {
		ENABLED_PAYMENTS_FOR_CHECK_PAYMENT = new ArrayList<>();
		ENABLED_PAYMENTS_FOR_CHECK_PAYMENT.add(PaymentType.AUTOMATIC);
	}

	private static void buildEnabledPaymentsForCreditCardStatementPayment() {
		ENABLED_PAYMENTS_FOR_CREDITCARD_STATEMENT_PAYMENT = new ArrayList<>();
		ENABLED_PAYMENTS_FOR_CREDITCARD_STATEMENT_PAYMENT.addAll(Arrays.asList(PaymentType.AUTOMATIC, PaymentType.BANK_SLIP, PaymentType.INTERNAL));
	}

	private static void buildEnabledPaymentsForDefaultTransaction() {
		ENABLED_PAYMENTS_FOR_DEFAULT_TRANSACTION = new ArrayList<>();
		for (PaymentType x : PaymentType.values())
			if (!x.equals(PaymentType.INTERNAL))
				ENABLED_PAYMENTS_FOR_DEFAULT_TRANSACTION.add(x);
	}

	private static void buildEnabledPaymentsForInternalTransaction() {
		ENABLED_PAYMENTS_FOR_INTERNAL_TRANSACTION = new ArrayList<>();
		ENABLED_PAYMENTS_FOR_INTERNAL_TRANSACTION.add(PaymentType.INTERNAL);
	}

	private static void buildEnabledPaymentsForTransferBetweenHolders() {
		ENABLED_PAYMENTS_FOR_TRANSFER_BETWEEN_HOLDERS = new ArrayList<>();
		ENABLED_PAYMENTS_FOR_TRANSFER_BETWEEN_HOLDERS.addAll(Arrays.asList(PaymentType.CASH, PaymentType.CHECK, PaymentType.DEPOSIT, PaymentType.DOC, PaymentType.INTERNAL, PaymentType.PIX, PaymentType.TED));
	}

	private static void buildEnabledPaymentsForWithdraw() {
		ENABLED_PAYMENTS_FOR_WITHDRAW = new ArrayList<>();
		ENABLED_PAYMENTS_FOR_WITHDRAW.add(PaymentType.WITHDRAW);
	}
}
