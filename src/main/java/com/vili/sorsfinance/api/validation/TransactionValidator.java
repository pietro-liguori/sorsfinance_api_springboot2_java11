package com.vili.sorsfinance.api.validation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.vili.sorsfinance.api.domain.Account;
import com.vili.sorsfinance.api.domain.BankAccount;
import com.vili.sorsfinance.api.domain.BankCard;
import com.vili.sorsfinance.api.domain.Card;
import com.vili.sorsfinance.api.domain.Category;
import com.vili.sorsfinance.api.domain.CreditCardStatement;
import com.vili.sorsfinance.api.domain.Payment;
import com.vili.sorsfinance.api.domain.Person;
import com.vili.sorsfinance.api.domain.Voucher;
import com.vili.sorsfinance.api.domain.VoucherAccount;
import com.vili.sorsfinance.api.domain.Wallet;
import com.vili.sorsfinance.api.domain.dto.PaymentDTO;
import com.vili.sorsfinance.api.domain.dto.TransactionDTO;
import com.vili.sorsfinance.api.domain.enums.AccountType;
import com.vili.sorsfinance.api.domain.enums.CardType;
import com.vili.sorsfinance.api.domain.enums.PaymentStatus;
import com.vili.sorsfinance.api.domain.enums.PaymentType;
import com.vili.sorsfinance.api.domain.enums.PersonProfile;
import com.vili.sorsfinance.api.domain.enums.TransactionDirection;
import com.vili.sorsfinance.api.domain.enums.TransactionType;
import com.vili.sorsfinance.api.repositories.AccountRepository;
import com.vili.sorsfinance.api.repositories.BankAccountRepository;
import com.vili.sorsfinance.api.repositories.VoucherAccountRepository;
import com.vili.sorsfinance.api.validation.constraints.ValidTransaction;
import com.vili.sorsfinance.framework.DTOType;
import com.vili.sorsfinance.framework.exceptions.FieldMessage;

public class TransactionValidator implements ConstraintValidator<ValidTransaction, TransactionDTO> {

	@Autowired
	AccountRepository accountRepo;
	@Autowired
	BankAccountRepository bankAccountRepo;
	@Autowired
	VoucherAccountRepository voucherAccountRepo;

	@Override
	public void initialize(ValidTransaction ann) {
	}

	@Override
	public boolean isValid(TransactionDTO dto, ConstraintValidatorContext context) {
		Validator validator = new Validator();

		if (dto.getMethod() == DTOType.INSERT) {
			validator.notEmpty("date", dto.getDate(), true);
			validator.length("description", dto.getDescription(), 5, 60, true);
			validator.positiveOrZero("discount", dto.getDiscount(), true);
			validator.enumValue("direction", dto.getDirection(), TransactionDirection.class, true);
			validator.entityId("recipientId", dto.getRecipientId(), Person.class, true);
			validator.entityIds("categoryIds", dto.getCategoryIds(), Category.class, true);
			
			if (validator.enumValue("type", dto.getType(), TransactionType.class, true) && validator.notEmpty("payments", dto.getPayments(), true)) {
				if (validateDefaultPayments(dto, validator)) {
					TransactionType transType = TransactionType.toEnum(dto.getType());
					List<PaymentDTO> payments = dto.getPayments();
					
					if (validator.size("payments", payments, 1, true)) {
						PaymentDTO payDto = payments.get(0);
						Person recipient = (Person) validator.entityId("holderId", dto.getRecipientId(), Person.class, true);
						
						switch (transType) {
						case CREDITCARD_STATEMENT_PAYMENT:
							if (recipient != null) {
								if (!PersonProfile.toEnum(recipient.getProfile()).equals(PersonProfile.BANK))
										validator.addError(new FieldMessage("holderId", "Must reference to a person with " + PersonProfile.BANK + " profile"));
							}
							
							validator.entityId("payments[0].creditCardStatementId", payDto.getCreditCardStatementId(), CreditCardStatement.class, true);
							break;
						case WITHDRAW:
							if (recipient != null) {
								if (!PersonProfile.toEnum(recipient.getProfile()).equals(PersonProfile.BANK))
										validator.addError(new FieldMessage("holderId", "Must reference to a person with " + PersonProfile.BANK + " profile"));
							}
							break;
						case TRANSFER_BETWEEN_HOLDERS:
							if (recipient != null) {
								if (!PersonProfile.toEnum(recipient.getProfile()).equals(PersonProfile.HOLDER))
										validator.addError(new FieldMessage("holderId", "Must reference to a person with " + PersonProfile.HOLDER + " profile"));
							}
							
							Account inAcc = (Account) validator.entityId("payments[0].transferAccountId", payDto.getTransferAccountId(), Account.class, true);
							if (inAcc != null) {
								Account outAcc = accountRepo.findById(payDto.getAccountId()).get();
								AccountType outAccType = AccountType.toEnum(outAcc.getType());
								AccountType inAccType = AccountType.toEnum(inAcc.getType());
								boolean bankAccMatch = Account.BANK_ACCOUNT_TYPES.containsAll(Arrays.asList(outAccType, inAccType));
								boolean providerAccMatch = Account.VOUCHER_ACCOUNT_TYPES.containsAll(Arrays.asList(outAccType, inAccType));
								boolean walletMatch = Account.WALLET_TYPES.containsAll(Arrays.asList(outAccType, inAccType));
								boolean isMatch = bankAccMatch || providerAccMatch || walletMatch;

								if (!isMatch)
									validator.addError(new FieldMessage("payments[0].transferAccountId", "Destination account type bundle must match origin account type bundle"));
							}
							break;
						default:
							break;
						}
					}
				}
			}
		} else {

		}

		return validator.validate(context);
	}

	public boolean validateDefaultPayments(TransactionDTO dto, Validator validator) {
		List<PaymentDTO> payments = dto.getPayments();
		TransactionType transType = TransactionType.toEnum(dto.getType());

		for (int i = 0; i < payments.size(); i++) {
			PaymentDTO payDTO = payments.get(i);
			validator.enumValue("payments[" + i + "]status", payDTO.getStatus(), PaymentStatus.class, true);
			validator.length("payments[" + i + "].description", payDTO.getDescription(), 5, 80, false);

			
			Person responsible = (Person) validator.entityId("responsibleId", payDTO.getResponsibleId(), Person.class, true);
			
			if (responsible != null) {
				if (TransactionDirection.toEnum(dto.getDirection()).equals(TransactionDirection.OUTPUT) && !PersonProfile.toEnum(responsible.getProfile()).equals(PersonProfile.HOLDER))
						validator.addError(new FieldMessage("payments[" + i + "].responsibleId", "Must reference to a person with " + PersonProfile.HOLDER + " profile"));
			}
			
			if (validator.enumValue("payments[" + i + "].type", payDTO.getType(), PaymentType.class, true)) {
				PaymentType payType = PaymentType.toEnum(payDTO.getType());

				if (!transType.getPaymentTypes().contains(payType)) {
					validator.addError(new FieldMessage("payments[" + i + "].type", "Payment of type '" + payType
							+ "' not allowed for transactions of type '" + transType + "'"));
				}
				
				Account account = (Account) validator.entityId("payments[" + i + "].accountId", payDTO.getAccountId(), Account.class, true);
				
				if (account != null && validator.positive("value", payDTO.getValue(), true)) {
					AccountType accType = AccountType.toEnum(account.getType());
					Double availableBalance = 0.0;

					if (Account.BANK_ACCOUNT_TYPES.contains(accType)) {
						availableBalance = ((BankAccount) account).getBalance() + ((BankAccount) account).getOverdraft();
						
						if (payDTO.getValue() > availableBalance)
							validator.addError(new FieldMessage("payments[" + i + "].accountId", "BankAccount(id=" + payDTO.getAccountId() + ") does not have enought balance to carry out this payment"));
					}
					
					if (Account.WALLET_TYPES.contains(accType)) {
						availableBalance = ((Wallet) account).getBalance();
						
						if (payDTO.getValue() > availableBalance)
							validator.addError(new FieldMessage("payments[" + i + "].accountId", "Wallet(id=" + payDTO.getAccountId() + ") does not have enought balance to carry out this payment"));
					}

					if (Payment.INSTALLMENT_PAYMENT_TYPES.contains(payType))
						validator.positive("payments[" + i + "].installments", payDTO.getInstallments(), true);

					List<CardType> cardTypes = accType.getEnabledCardsByPaymentType().get(payType);

					if (cardTypes == null) {
						validator.addError(new FieldMessage("payments[" + i + "].accountId", "Payment of type '" + payType + "' not allowed for referenced account(id=" + payDTO.getAccountId() + ") of type '" + accType + "'"));
					} else {
						Long cardId = payDTO.getCardId();
						
						if (cardId == null) {
							if (!cardTypes.isEmpty()) {
								validator.addError(new FieldMessage("payments[" + i + "].cardId",
										"Must not be null for referenced payment of type '" + payType
												+ "' and account of type '" + accType + "'"));
							}
						} else {
							Card card = (Card) validator.entityId("payments[" + i + "].cardId", cardId, Card.class, true);
							
							if (card != null) {
								if (!cardTypes.isEmpty()) {
									CardType cardType = CardType.toEnum(card.getType());
									boolean bankAccountMatch = false;
									boolean providerAccountMatch = false;
									
									if (Card.BANK_CARD_TYPES.contains(cardType)) {
										BankAccount accProbe = new BankAccount();
										accProbe.setId(payDTO.getAccountId());
										accProbe.addCard((BankCard) card);
										bankAccountMatch = bankAccountRepo.findOne(Example.of(accProbe)).isPresent();
									}

									if (Card.VOUCHER_TYPES.contains(cardType)) {
										VoucherAccount accProbe = new VoucherAccount();
										accProbe.setId(payDTO.getAccountId());
										accProbe.addVoucher((Voucher) card);
										Optional<VoucherAccount> aux = voucherAccountRepo.findOne(Example.of(accProbe));
										providerAccountMatch = aux.isPresent();

										if (providerAccountMatch) {
											Voucher voucher = (Voucher) aux.get().getVouchers().stream().filter(vc -> vc.getId().equals(cardId)).toList().get(0);
											availableBalance = voucher.getBalance();

											if (payDTO.getValue() > availableBalance)
												validator.addError(new FieldMessage("payments[" + i + "].cardId", "Voucher(id=" + payDTO.getCardId() + ") does not have enought balance to carry out this payment"));
										}
									}

									boolean cardBelongsToAccount = bankAccountMatch || providerAccountMatch;

									if (!cardBelongsToAccount)
										validator.addError(new FieldMessage("payments[" + i + "].cardId", "Card(id=" + cardId + ") of type '" + card.getType() + "' does not belong to referenced account(id=" + payDTO.getAccountId() + ") of type '" + accType + "'"));
									
								} else
									validator.addError(new FieldMessage("payments[" + i + "].cardId", "Must be null for referenced payment of type '" + payType + "' and account of type '" + accType + "'"));
							}
						}
					}
				}
			}
		}
		
		return validator.getErrors().isEmpty();
	}
}
