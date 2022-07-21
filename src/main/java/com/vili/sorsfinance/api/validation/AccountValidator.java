package com.vili.sorsfinance.api.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.vili.sorsfinance.api.domain.Account;
import com.vili.sorsfinance.api.domain.BankAccount;
import com.vili.sorsfinance.api.domain.Card;
import com.vili.sorsfinance.api.domain.Person;
import com.vili.sorsfinance.api.domain.TicketAccount;
import com.vili.sorsfinance.api.domain.dto.AccountDTO;
import com.vili.sorsfinance.api.domain.enums.AccountType;
import com.vili.sorsfinance.api.domain.enums.PeriodUnit;
import com.vili.sorsfinance.api.domain.enums.PersonProfile;
import com.vili.sorsfinance.api.repositories.AccountRepository;
import com.vili.sorsfinance.api.repositories.BankAccountRepository;
import com.vili.sorsfinance.api.repositories.CardRepository;
import com.vili.sorsfinance.api.repositories.PersonRepository;
import com.vili.sorsfinance.api.repositories.TicketAccountRepository;
import com.vili.sorsfinance.api.validation.constraints.ValidAccount;
import com.vili.sorsfinance.framework.FieldMessage;
import com.vili.sorsfinance.framework.enums.DTOType;
import com.vili.sorsfinance.framework.exceptions.EnumValueNotFoundException;

public class AccountValidator implements ConstraintValidator<ValidAccount, AccountDTO> {

	@Autowired
	AccountRepository accountRepo;
	@Autowired
	BankAccountRepository bankAccountRepo;
	@Autowired
	CardRepository cardRepo;
	@Autowired
	PersonRepository personRepo;
	@Autowired
	TicketAccountRepository ticketAccountRepo;

	@Override
	public void initialize(ValidAccount ann) {

	}

	@Override
	public boolean isValid(AccountDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		Example<Account> ex;

		if (dto.getMethod() == DTOType.INSERT) {
			ex = Example.of(new Account(null, dto.getName(), null, null, null, null, null));
			List<Account> accounts = accountRepo.findAll(ex);

			if (!accounts.isEmpty() && dto.getName() != null)
				list.add(new FieldMessage("name", "'" + dto.getName() + "' already in use"));

			if (dto.getType() != null) {
				try {
					AccountType type = AccountType.toEnum(dto.getType());

					if (Account.BANK_ACCOUNT_TYPES.contains(type))
						list.addAll(validateBankAccount(dto, context));
					else if (Account.TICKET_ACCOUNT_TYPES.contains(type))
						list.addAll(validateTicketAccount(dto, context));
					else if (Account.WALLET_TYPES.contains(type))
						list.addAll(validateWallet(dto, context));
				} catch (EnumValueNotFoundException e) {
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

	private List<FieldMessage> validateBankAccount(AccountDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (dto.getBankId() == null) {
			list.add(new FieldMessage("bankId", "Must not be null"));
		} else {
			Optional<Person> aux = personRepo.findById(dto.getBankId());

			if (aux.isEmpty())
				list.add(new FieldMessage("bankId", "Resource not found: " + dto.getBankId()));
			else if (!aux.get().getProfile().equals(PersonProfile.BANK.getLabel()))
				list.add(new FieldMessage("bankId",
						"Must reference to a person with " + PersonProfile.BANK + " profile"));
		}

		if (dto.getCardIds() != null) {
			for (Long cardId : dto.getCardIds()) {

				if (cardId == null) {
					list.add(new FieldMessage("cardIds[]", "Must not be null. Delete null elements from list"));
				} else {
					Optional<Card> aux = cardRepo.findById(cardId);

					if (aux.isEmpty())
						list.add(new FieldMessage("cardIds[]", "Resource not found: " + cardId));
					else {
						List<BankAccount> accounts = bankAccountRepo.findByCardsId(cardId);

						if (!accounts.isEmpty())
							list.add(new FieldMessage("cardIds[]", "Card '" + cardId
									+ "' already referenced to bank account: " + accounts.get(0).getName()));
					}
				}
			}
		}

		if (dto.getNumber() == null || dto.getNumber().isBlank())
			list.add(new FieldMessage("number", "Must not be null or empty"));

		if (dto.getAgency() == null || dto.getAgency().isBlank())
			list.add(new FieldMessage("agency", "Must not be null or empty"));

		if (dto.getOverdraft() == null) {
			list.add(new FieldMessage("overdraft", "Must not be null"));
		} else {
			if (dto.getOverdraft().isNaN() || dto.getOverdraft() < 0)
				list.add(new FieldMessage("overdraft", "Must be not be negative"));
		}

		if (dto.getInterest() == null) {
			list.add(new FieldMessage("interest", "Must not be null"));
		} else {
			if (dto.getInterest().isNaN() || dto.getInterest() < 0)
				list.add(new FieldMessage("interest", "Must be not be negative"));
		}

		if (dto.getInterestUnit() == null) {
			list.add(new FieldMessage("interestUnit", "Must not be null"));
		} else {
			EnumValidator.validate(dto.getInterestUnit(), PeriodUnit.class)
					.forEach(e -> list.add(new FieldMessage("interestUnit", e.getMessage())));
		}

		if (dto.getGracePeriod() == null) {
			list.add(new FieldMessage("gracePeriod", "Must not be null"));
		} else {
			if (dto.getGracePeriod() < 0)
				list.add(new FieldMessage("gracePeriod", "Must be not be negative"));
		}

		if (dto.getGracePeriodUnit() == null) {
			list.add(new FieldMessage("gracePeriodUnit", "Must not be null"));
		} else {
			EnumValidator.validate(dto.getGracePeriodUnit(), PeriodUnit.class)
					.forEach(e -> list.add(new FieldMessage("gracePeriodUnit", e.getMessage())));
		}

		if (dto.getCreditLimit() == null) {
			list.add(new FieldMessage("creditLimit", "Must not be null"));
		} else {
			if (dto.getCreditLimit().isNaN() || dto.getCreditLimit() < 0)
				list.add(new FieldMessage("creditLimit", "Must be not be negative"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
					.addConstraintViolation();
		}

		return list;
	}

	private List<FieldMessage> validateTicketAccount(AccountDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (dto.getBankId() == null) {
			list.add(new FieldMessage("bankId", "Must not be null"));
		} else {
			Optional<Person> aux = personRepo.findById(dto.getBankId());

			if (aux.isEmpty())
				list.add(new FieldMessage("bankId", "Resource not found: " + dto.getBankId()));
			else if (!aux.get().getProfile().equals(PersonProfile.BANK.getLabel()))
				list.add(new FieldMessage("bankId",
						"Must reference to a person with " + PersonProfile.BANK + " profile"));
		}

		if (dto.getCardIds() != null) {
			for (Long cardId : dto.getCardIds()) {

				if (cardId == null) {
					list.add(new FieldMessage("cardIds[]", "Must not be null. Delete null elements"));
				} else {
					Optional<Card> aux = cardRepo.findById(cardId);

					if (aux.isEmpty())
						list.add(new FieldMessage("cardIds[]",
								"Resource not found: " + cardId));
					else {
						List<TicketAccount> accounts = ticketAccountRepo.findByCardsId(cardId);

						if (!accounts.isEmpty())
							list.add(new FieldMessage("cardIds[]", "Card '" + cardId
									+ "' already referenced to ticket account: " + accounts.get(0).getName()));
					}
				}
			}
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
					.addConstraintViolation();
		}

		return list;
	}

	private List<FieldMessage> validateWallet(AccountDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (dto.getSavings() == null) {
			list.add(new FieldMessage("savings", "Must not be null"));
		} else {
			if (dto.getSavings().isNaN() || dto.getSavings() < 0)
				list.add(new FieldMessage("savings", "Must be not be negative"));
		}

		return list;
	}
}
