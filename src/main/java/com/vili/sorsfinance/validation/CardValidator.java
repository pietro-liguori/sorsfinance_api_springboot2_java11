package com.vili.sorsfinance.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.entities.Account;
import com.vili.sorsfinance.api.entities.Card;
import com.vili.sorsfinance.api.entities.dto.CardDTO;
import com.vili.sorsfinance.api.entities.enums.AccountType;
import com.vili.sorsfinance.api.entities.enums.CardType;
import com.vili.sorsfinance.api.entities.enums.PaymentType;
import com.vili.sorsfinance.api.entities.enums.PeriodUnit;
import com.vili.sorsfinance.api.exceptions.EnumValueNotFoundException;
import com.vili.sorsfinance.api.framework.DTOType;
import com.vili.sorsfinance.api.framework.FieldMessage;
import com.vili.sorsfinance.api.repositories.AccountRepository;
import com.vili.sorsfinance.validation.constraints.ValidCard;

public class CardValidator implements ConstraintValidator<ValidCard, CardDTO> {

	@Autowired
	AccountRepository accountRepo;

	@Override
	public void initialize(ValidCard ann) {
	}

	@Override
	public boolean isValid(CardDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (dto.getMethod() == DTOType.INSERT) {
			if (dto.getType() != null) {
				try {
					CardType cardType = CardType.toEnum(dto.getType());

					if (dto.getAccountId() != null) {
						Optional<Account> aux = accountRepo.findById(dto.getAccountId());

						if (aux.isEmpty())
							list.add(new FieldMessage("accountId", "Account not found: " + dto.getAccountId()));
						else {
							AccountType accountType = AccountType.toEnum(aux.get().getType());
							boolean isCardTypeAllowed = false;
							boolean noCardsAllowed = true;

							for (Entry<PaymentType, List<CardType>> entry : accountType.getPaymentCardsMap().entrySet()) {
								if (entry.getValue().contains(cardType))
									isCardTypeAllowed = true;
								
								if (noCardsAllowed && !entry.getValue().isEmpty())
									noCardsAllowed = false;
							}
							
							if (!isCardTypeAllowed) {
								if (noCardsAllowed)
									list.add(new FieldMessage("accountId",
											"Referenced account(id=" + dto.getAccountId() + ") of type '" + accountType
													+ "' is not allowed to have a cards"));
								else
									list.add(new FieldMessage("accountId",
											"Referenced account(id=" + dto.getAccountId() + ") of type '" + accountType
													+ "' is not allowed to have a card of type '" + cardType + "'"));
							}
						}
					}

					if (Card.CREDIT_CARD_TYPES.contains(cardType)) {
						if (dto.getClosingDay() == null) {
							list.add(new FieldMessage("closingDay", "Must not be null"));
						} else {
							if (dto.getClosingDay() < 1 || dto.getClosingDay() > 31) {
								list.add(new FieldMessage("closingDay", "Must not be between 1 and 31"));
							}
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
					}
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
}
