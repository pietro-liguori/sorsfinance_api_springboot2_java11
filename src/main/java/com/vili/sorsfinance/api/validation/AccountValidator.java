package com.vili.sorsfinance.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.vili.sorsfinance.api.domain.Account;
import com.vili.sorsfinance.api.domain.BankAccount;
import com.vili.sorsfinance.api.domain.BankCard;
import com.vili.sorsfinance.api.domain.Person;
import com.vili.sorsfinance.api.domain.Voucher;
import com.vili.sorsfinance.api.domain.VoucherAccount;
import com.vili.sorsfinance.api.domain.dto.AccountDTO;
import com.vili.sorsfinance.api.domain.enums.AccountStatus;
import com.vili.sorsfinance.api.domain.enums.AccountType;
import com.vili.sorsfinance.api.domain.enums.PeriodUnit;
import com.vili.sorsfinance.api.domain.enums.PersonProfile;
import com.vili.sorsfinance.api.validation.constraints.ValidAccount;
import com.vili.sorsfinance.framework.DTOType;
import com.vili.sorsfinance.framework.exceptions.FieldMessage;

public class AccountValidator implements ConstraintValidator<ValidAccount, AccountDTO> {

	@Override
	public void initialize(ValidAccount ann) {

	}

	@Override
	public boolean isValid(AccountDTO dto, ConstraintValidatorContext context) {
		Validator validator = new Validator();

		if (dto.getMethod().equals(DTOType.INSERT)) {
			validator.enumValue("status", dto.getType(), AccountStatus.class, true);
			Account probe = new Account();
			probe.setName(dto.getName());
			
			if (validator.length("name", dto.getName(), 5, 60, true))
				validator.unique("name", Example.of(probe, ExampleMatcher.matching().withIgnoreCase()), true);

			Person holder = (Person) validator.entityId("holderId", dto.getHolderId(), Person.class, true);
			
			if (holder != null) {
				if (!PersonProfile.toEnum(holder.getProfile()).equals(PersonProfile.HOLDER))
						validator.addError(new FieldMessage("holderId", "Must reference to a person with " + PersonProfile.HOLDER + " profile"));
			}
			
			if (validator.enumValue("type", dto.getType(), AccountType.class, true)) {
				AccountType type = AccountType.toEnum(dto.getType());

				if (Account.BANK_ACCOUNT_TYPES.contains(type))
					validateBankAccount(dto, validator);
				else if (Account.VOUCHER_ACCOUNT_TYPES.contains(type))
					validateVoucherAccount(dto, validator);
				else if (Account.WALLET_TYPES.contains(type))
					validateWallet(dto, validator);
			}
		} else {

		}

		return validator.validate(context);
	}

	private void validateBankAccount(AccountDTO dto, Validator validator) {
		if (dto.getMethod().equals(DTOType.INSERT)) {
			validator.notEmpty("agency", dto.getAgency(), true);
			validator.positiveOrZero("overdraft", dto.getOverdraft(), true);
			validator.positiveOrZero("intesrest", dto.getInterest(), true);
			validator.positiveOrZero("gracePeriod", dto.getGracePeriod(), true);
			validator.positiveOrZero("savings", dto.getSavings(), true);
			validator.enumValue("interestUnit", dto.getInterestUnit(), PeriodUnit.class, true);
			validator.enumValue("gracePeriodUnit", dto.getGracePeriodUnit(), PeriodUnit.class, true);

			if (validator.notEmpty("cardIds[]", dto.getVoucherIds(), false)) {
				for (Long cardId : dto.getCardIds()) {
					if (validator.notEmpty("cardIds[]", cardId, true)) {
						BankAccount probe = new BankAccount();
						probe.addCard(new BankCard(cardId));
						validator.unique("cardIds[]", Example.of(probe, ExampleMatcher.matching().withIgnoreCase()), true);
					}
				}
			}

			validateVoucherAccount(dto, validator);
		} else {
			
		}
	}

	private void validateVoucherAccount(AccountDTO dto, Validator validator) {
		if (dto.getMethod().equals(DTOType.INSERT)) {
			validator.notEmpty("number", dto.getNumber(), true);
			Person bank = (Person) validator.entityId("bankId", dto.getBankId(), Person.class, true);
			
			if (bank != null) {
				if (!PersonProfile.toEnum(bank.getProfile()).equals(PersonProfile.BANK))
					validator.addError(new FieldMessage("bankId", "Must reference to a person with " + PersonProfile.BANK + " profile"));
			}
			
			if (validator.notEmpty("voucherIds[]", dto.getVoucherIds(), false)) {
				for (Long cardId : dto.getCardIds()) {
					if (validator.notEmpty("voucherIds[]", cardId, true)) {
						VoucherAccount probe = new VoucherAccount();
						probe.addVoucher(new Voucher(cardId));
						validator.unique("voucherIds[]", Example.of(probe, ExampleMatcher.matching().withIgnoreCase()), true);
					}
				}
			}
		} else {
			
		}
	}

	private void validateWallet(AccountDTO dto, Validator validator) {
		if (dto.getMethod().equals(DTOType.INSERT)) {
			validator.notEmpty("balance", dto.getBalance(), true);
			validator.positiveOrZero("savings", dto.getSavings(), true);
		} else {
			
		}
	}
}
