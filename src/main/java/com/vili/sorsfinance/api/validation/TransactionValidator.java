package com.vili.sorsfinance.api.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.domain.Account;
import com.vili.sorsfinance.api.domain.BankAccount;
import com.vili.sorsfinance.api.domain.Card;
import com.vili.sorsfinance.api.domain.CreditCardStatement;
import com.vili.sorsfinance.api.domain.Payment;
import com.vili.sorsfinance.api.domain.Person;
import com.vili.sorsfinance.api.domain.TicketAccount;
import com.vili.sorsfinance.api.domain.dto.PaymentDTO;
import com.vili.sorsfinance.api.domain.dto.TransactionDTO;
import com.vili.sorsfinance.api.domain.enums.AccountType;
import com.vili.sorsfinance.api.domain.enums.CardType;
import com.vili.sorsfinance.api.domain.enums.PaymentStatus;
import com.vili.sorsfinance.api.domain.enums.PaymentType;
import com.vili.sorsfinance.api.domain.enums.PersonProfile;
import com.vili.sorsfinance.api.domain.enums.TransactionType;
import com.vili.sorsfinance.api.repositories.AccountRepository;
import com.vili.sorsfinance.api.repositories.BankAccountRepository;
import com.vili.sorsfinance.api.repositories.CardRepository;
import com.vili.sorsfinance.api.repositories.CreditCardStatementRepository;
import com.vili.sorsfinance.api.repositories.PersonRepository;
import com.vili.sorsfinance.api.repositories.TicketAccountRepository;
import com.vili.sorsfinance.api.validation.constraints.ValidTransaction;
import com.vili.sorsfinance.framework.FieldMessage;
import com.vili.sorsfinance.framework.enums.DTOType;
import com.vili.sorsfinance.framework.exceptions.EnumValueNotFoundException;

public class TransactionValidator implements ConstraintValidator<ValidTransaction, TransactionDTO> {

	@Autowired
	AccountRepository accountRepo;
	@Autowired
	BankAccountRepository bankAccountRepo;
	@Autowired
	CardRepository cardRepo;
	@Autowired
	CreditCardStatementRepository creditCardStatementRepo;
	@Autowired
	PersonRepository personRepo;
	@Autowired
	TicketAccountRepository ticketAccountRepo;

	@Override
	public void initialize(ValidTransaction ann) {
	}

	@Override
	public boolean isValid(TransactionDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (dto.getMethod() == DTOType.INSERT) {
			if (dto.getType() != null) {
				if (dto.getPayments() != null && !dto.getPayments().isEmpty()) {
					try {
						validateDefault(dto).forEach(e -> list.add(e));
						TransactionType transType = TransactionType.toEnum(dto.getType());
						List<PaymentDTO> payments = dto.getPayments().stream().toList();

						if (list.isEmpty()) {
							switch (transType) {
							case CREDITCARD_STATEMENT_PAYMENT:
								if (payments.size() != 1) {
									list.add(new FieldMessage("payments",
											"Transactions of type '" + TransactionType.CREDITCARD_STATEMENT_PAYMENT
													+ "' must have only one payment"));
								} else {
									PaymentDTO pay = payments.get(0);

									if (pay.getCreditCardStatementId() == null) {
										list.add(new FieldMessage("payments[0].creditCardStatementId",
												"Must not be null"));
									} else {
										Optional<CreditCardStatement> aux = creditCardStatementRepo
												.findById(pay.getCreditCardStatementId());
										if (aux.isEmpty()) {
											list.add(new FieldMessage("payments[0].creditCardStatementId",
													"Credit card statement not found: "
															+ pay.getCreditCardStatementId()));
										}
									}
								}

								Optional<Person> aux = personRepo.findById(dto.getRecipientId());

								if (aux.isPresent()) {
									if (!PersonProfile.toEnum(aux.get().getProfile()).equals(PersonProfile.BANK)) {
										list.add(new FieldMessage("recipientId",
												"Transactions of type '" + TransactionType.CREDITCARD_STATEMENT_PAYMENT
														+ "' require a recipient of profile '" + PersonProfile.BANK
														+ "'"));
									}
								}
								break;
							case WITHDRAW:
								if (payments.size() != 1) {
									list.add(new FieldMessage("payments", "Transactions of type '"
											+ TransactionType.WITHDRAW + "' must have only one payment"));
								}

								aux = personRepo.findById(dto.getRecipientId());

								if (aux.isPresent()) {
									if (!PersonProfile.toEnum(aux.get().getProfile()).equals(PersonProfile.BANK)) {
										list.add(new FieldMessage("recipientId",
												"Transactions of type '" + TransactionType.WITHDRAW
														+ "' require a recipient of profile '" + PersonProfile.BANK
														+ "'"));
									}
								}
								break;
							case BETWEEN_HOLDERS:
								if (payments.size() != 1) {
									list.add(new FieldMessage("payments", "Transactions of type '"
											+ TransactionType.BETWEEN_HOLDERS + "' must have only one payment"));
								} else {
									PaymentDTO pay = payments.get(0);

									if (pay.getTransferAccountId() == null)
										list.add(new FieldMessage("payments[0].transferAccountId", "Must not be null"));
									else {
										Optional<Account> inAcc = accountRepo.findById(pay.getTransferAccountId());
										Optional<Account> outAcc = accountRepo.findById(pay.getAccountId());

										if (inAcc.isEmpty()) {
											list.add(new FieldMessage("payments[0].transferAccountId",
													"Account not found: " + pay.getTransferAccountId()));
										} else {
											AccountType outAccType = AccountType.toEnum(outAcc.get().getType());
											AccountType inAccType = AccountType.toEnum(inAcc.get().getType());
											boolean bankAccMatch = Account.BANK_ACCOUNT_TYPES
													.containsAll(Arrays.asList(outAccType, inAccType));
											boolean ticketAccMatch = Account.TICKET_ACCOUNT_TYPES
													.containsAll(Arrays.asList(outAccType, inAccType));
											boolean walletMatch = Account.WALLET_TYPES
													.containsAll(Arrays.asList(outAccType, inAccType));
											boolean isMatch = bankAccMatch || ticketAccMatch || walletMatch;

											if (!isMatch) {
												list.add(new FieldMessage("payments[0].transferAccountId",
														"Destination account type bundle must match origin account type bundle"));
											}
										}
									}
								}

								aux = personRepo.findById(dto.getRecipientId());

								if (aux.isPresent()) {
									if (!PersonProfile.toEnum(aux.get().getProfile()).equals(PersonProfile.HOLDER)) {
										list.add(new FieldMessage("recipientId",
												"Transactions of type '" + TransactionType.BETWEEN_HOLDERS
														+ "' require a recipient of profile '" + PersonProfile.HOLDER
														+ "'"));
									}
								}
								break;
							default:
								break;
							}
						}
					} catch (EnumValueNotFoundException e) {
					}
				}
			}
		} else if (dto.getMethod() == DTOType.UPDATE) {
			// TODO all account types update validation
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
					.addConstraintViolation();
		}

		return list.isEmpty();
	}

	public List<FieldMessage> validateDefault(TransactionDTO dto) {
		List<FieldMessage> list = new ArrayList<>();
		List<PaymentDTO> payments = dto.getPayments().stream().toList();

		try {
			TransactionType transType = TransactionType.toEnum(dto.getType());

			for (int i = 0; i < payments.size(); i++) {
				PaymentDTO pay = payments.get(i);

				if (pay.getType() == null)
					list.add(new FieldMessage("payments[" + i + "].type", "Must not be null"));
				else {
					String field = "payments[" + i + "].type";
					EnumValidator.validate(pay.getType(), PaymentType.class)
							.forEach(e -> list.add(new FieldMessage(field, e.getMessage())));
				}

				if (list.isEmpty()) {
					PaymentType payType = PaymentType.toEnum(pay.getType());

					if (!transType.getPaymentTypes().contains(payType))
						list.add(new FieldMessage("payments[" + i + "].type", "Payment of type '" + payType
								+ "' not allowed for transactions of type '" + transType + "'"));

					if (pay.getAccountId() == null)
						list.add(new FieldMessage("payments[" + i + "].accountId", "Must not be null"));
					else {
						Optional<Account> auxAccount = accountRepo.findById(pay.getAccountId());

						if (auxAccount.isEmpty())
							list.add(new FieldMessage("payments[" + i + "].accountId",
									"Account not found: " + pay.getAccountId()));
						else {
							Class<?> sorsClass = auxAccount.get().getDomainClass();
							Double totalBalance = auxAccount.get().getBalance();

							if (sorsClass.equals(BankAccount.class)) {
								Optional<BankAccount> bkAcc = bankAccountRepo.findById(pay.getAccountId());

								if (bkAcc.isPresent())
									totalBalance = bkAcc.get().getBalance() + bkAcc.get().getOverdraft();
							} else if (sorsClass.equals(TicketAccount.class)) {
								Optional<TicketAccount> bkAcc = ticketAccountRepo.findById(pay.getAccountId());

								if (bkAcc.isPresent())
									totalBalance = bkAcc.get().getBalance();
							}

							if (pay.getValue() > totalBalance)
								list.add(new FieldMessage("payments[" + i + "].accountId",
										"Account(id=" + pay.getAccountId()
												+ ") does not have enought balance to carry out this payment"));

							AccountType accType = AccountType.toEnum(auxAccount.get().getType());
							List<CardType> cardTypes = accType.getPaymentCardsMap().get(payType);

							if (cardTypes == null) {
								list.add(new FieldMessage("payments[" + i + "].accountId",
										"Payment of type '" + payType + "' not allowed for referenced account(id="
												+ pay.getAccountId() + ") of type '" + accType + "'"));
							} else {
								if (pay.getCardId() == null) {
									if (!cardTypes.isEmpty()) {
										list.add(new FieldMessage("payments[" + i + "].cardId",
												"Must not be null for referenced payment of type '" + payType
														+ "' and account of type '" + accType + "'"));
									}
								} else {
									Optional<Card> auxCard = cardRepo.findById(pay.getCardId());

									if (auxCard.isEmpty())
										list.add(new FieldMessage("payments[" + i + "].cardId",
												"Card not found: " + pay.getAccountId()));

									if (!cardTypes.isEmpty()) {
										boolean bankAccountMatch = Account.BANK_ACCOUNT_TYPES.contains(accType)
												&& bankAccountRepo.findByCardsId(pay.getCardId()).isEmpty();
										boolean ticketAccountMatch = Account.TICKET_ACCOUNT_TYPES.contains(accType)
												&& ticketAccountRepo.findByCardsId(pay.getCardId()).isEmpty();
										boolean isMatch = bankAccountMatch || ticketAccountMatch;

										if (isMatch)
											list.add(new FieldMessage("payments[" + i + "].cardId",
													"Card(id=" + pay.getCardId() + ") of type '"
															+ CardType.toEnum(auxCard.get().getType())
															+ "' does not belong to referenced account(id="
															+ pay.getAccountId() + ") of type '" + accType + "'"));
									} else {
										list.add(new FieldMessage("payments[" + i + "].cardId",
												"Must be null for referenced payment of type '" + payType
														+ "' and account of type '" + accType + "'"));
									}
								}
							}

							if (Payment.INSTALLMENT_PAYMENT_TYPES.contains(payType))
								if (pay.getInstallments() == null)
									list.add(new FieldMessage("payments[" + i + "].installments", "Must not be null"));
								else if (pay.getInstallments() < 1)
									list.add(new FieldMessage("payments[" + i + "].installments", "Must be positive"));
						}
					}
				}

				if (pay.getStatus() == null)
					list.add(new FieldMessage("payments[" + i + "].status", "Must not be null"));
				else
					EnumValidator.validate(pay.getStatus(), PaymentStatus.class)
							.forEach(e -> list.add(new FieldMessage("status", e.getMessage())));

				if (pay.getResponsibleId() == null)
					list.add(new FieldMessage("payments[" + i + "].responsibleId", "Must not be null"));
				else {
					Optional<Person> auxPerson = personRepo.findById(pay.getResponsibleId());

					if (auxPerson.isEmpty())
						list.add(new FieldMessage("payments[" + i + "].responsibleId",
								"Account not found: " + pay.getAccountId()));
					else if (!PersonProfile.toEnum(auxPerson.get().getProfile()).equals(PersonProfile.HOLDER))
						list.add(new FieldMessage("payments[" + i + "].responsibleId",
								"Must reference to a person with holder profile"));
				}

				if (pay.getDescription() == null || pay.getDescription().trim() == "")
					list.add(new FieldMessage("payments[" + i + "].description", "Must not be null or empty"));
				else if (pay.getDescription().length() < 5 || pay.getDescription().length() > 80)
					list.add(
							new FieldMessage("payments[" + i + "].description", "Must be between 5 and 80 characters"));

				if (pay.getValue() == null)
					list.add(new FieldMessage("payments[" + i + "].value", "Must not be null"));
				else if (pay.getValue() <= 0)
					list.add(new FieldMessage("payments[" + i + "].value", "Must be positive"));
			}
		} catch (EnumValueNotFoundException e) {
		}

		return list;
	}
}
