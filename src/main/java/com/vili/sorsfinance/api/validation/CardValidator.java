package com.vili.sorsfinance.api.validation;

import java.util.List;
import java.util.Map.Entry;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.domain.Account;
import com.vili.sorsfinance.api.domain.Card;
import com.vili.sorsfinance.api.domain.dto.CardDTO;
import com.vili.sorsfinance.api.domain.enums.AccountType;
import com.vili.sorsfinance.api.domain.enums.CardStatus;
import com.vili.sorsfinance.api.domain.enums.CardType;
import com.vili.sorsfinance.api.domain.enums.PaymentType;
import com.vili.sorsfinance.api.domain.enums.PeriodUnit;
import com.vili.sorsfinance.api.repositories.AccountRepository;
import com.vili.sorsfinance.api.validation.constraints.ValidCard;
import com.vili.sorsfinance.framework.DTOType;
import com.vili.sorsfinance.framework.exceptions.FieldMessage;

public class CardValidator implements ConstraintValidator<ValidCard, CardDTO> {

	@Autowired
	AccountRepository accountRepo;

	@Override
	public void initialize(ValidCard ann) {
	}

	@Override
	public boolean isValid(CardDTO dto, ConstraintValidatorContext context) {
		Validator validator = new Validator();

		if (dto.getMethod() == DTOType.INSERT) {
			validator.length("name", dto.getName(), 5, 60, true);
			validator.enumValue("status", dto.getStatus(), CardStatus.class, true);
			
			if (validator.enumValue("type", dto.getType(), CardType.class, true)) {
				CardType type = CardType.toEnum(dto.getType());
				Account account = (Account) validator.entityId("accountId", dto.getAccountId(), Account.class, true);
				if (account != null) {
					AccountType accountType = AccountType.toEnum(account.getType());
					boolean isCardTypeAllowed = false;
					boolean noCardsAllowed = true;

					for (Entry<PaymentType, List<CardType>> entry : accountType.getEnabledCardsByPaymentType().entrySet()) {
						if (entry.getValue().contains(type))
							isCardTypeAllowed = true;
						
						if (noCardsAllowed && !entry.getValue().isEmpty())
							noCardsAllowed = false;
					}
					
					if (!isCardTypeAllowed) {
						if (noCardsAllowed)
							validator.addError(new FieldMessage("accountId",
									"Referenced account(id=" + dto.getAccountId() + ") of type '" + accountType
											+ "' is not allowed to have cards"));
						else
							validator.addError(new FieldMessage("accountId",
									"Referenced account(id=" + dto.getAccountId() + ") of type '" + accountType
											+ "' is not allowed to have a card of type '" + type + "'"));
					}
				}

				if (Card.BANK_CARD_TYPES.contains(type)) {
					validator.notEmpty("printedName", dto.getPrintedName(), true);
					validator.future("expiration", dto.getExpiration(), true);
					
					if (Card.CREDIT_CARD_TYPES.contains(type)) {
						validator.range("closingDay", dto.getClosingDay(), 1, 31, true);
						validator.positiveOrZero("gracePeriod", dto.getGracePeriod(), true);
						validator.positiveOrZero("interestUnit", dto.getInterest(), true);
						validator.enumValue("gracePeriodUnit", dto.getGracePeriodUnit(), PeriodUnit.class, true);
						validator.enumValue("interestUnit", dto.getInterestUnit(), PeriodUnit.class, true);
						validator.notEmpty("limit", dto.getLimit(), true);
					}
				} else if (Card.VOUCHER_TYPES.contains(type)) {
					validator.notEmpty("balance", dto.getBalance(), true);
					validator.future("expiration", dto.getExpiration(), true);
				}
			}
		} else {

		}

		return validator.validate(context);
	}
}
