package com.vili.sorsfinance.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.vili.sorsfinance.api.entities.Account;
import com.vili.sorsfinance.api.entities.dto.AccountDTO;
import com.vili.sorsfinance.api.entities.enums.AccountType;
import com.vili.sorsfinance.api.framework.DTOType;
import com.vili.sorsfinance.api.framework.FieldMessage;
import com.vili.sorsfinance.api.repositories.AccountRepository;
import com.vili.sorsfinance.api.repositories.CardRepository;
import com.vili.sorsfinance.api.repositories.PersonRepository;

public class AccountValidator implements ConstraintValidator<AccountSave, AccountDTO> {

	@Autowired
	AccountRepository accountRepo;
	@Autowired
	CardRepository cardRepo;
	@Autowired
	PersonRepository personRepo;

	@Override
	public void initialize(AccountSave ann) {

	}

	@Override
	public boolean isValid(AccountDTO dto, ConstraintValidatorContext context) {
		if (dto.getMethod() == DTOType.INSERT) {
			List<FieldMessage> list = new ArrayList<>();
			
			if (!personRepo.existsById(dto.getHolderId()))
				list.add(new FieldMessage("holder", "Id '" + dto.getHolderId() + "' does not exists. Save it before saving this account"));
			
			if (accountRepo.exists(Example.of(new Account(null, dto.getName(), null, null, null, null, null))))
				list.add(new FieldMessage("name", "'" + dto.getName() + "' already in use"));
			
			AccountType type =  AccountType.toEnum(dto.getType());

			if (Account.BANK_ACCOUNT_TYPES.contains(type) || Account.TICKET_ACCOUNT_TYPES.contains(type)) {
				if (!personRepo.existsById(dto.getBankId()))
					list.add(new FieldMessage("bank", "Id'" + dto.getBankId() + "' does not exists. Save it before saving this account"));
				
				for (Long cardId : dto.getCards()) {
					if (!cardRepo.existsById(cardId))
						list.add(new FieldMessage("cards", "Id '" + cardId + "' does not exists. Save it before saving this account"));			
				}

				if (Account.BANK_ACCOUNT_TYPES.contains(type)) {
					// TODO bank account insert validation
				}
			} else if (Account.WALLET_TYPES.contains(type)) {
				// TODO wallet insert validation
			}

			for (FieldMessage e : list) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
						.addConstraintViolation();
			}

			return list.isEmpty();
		} else if (dto.getMethod() == DTOType.UPDATE) {
			List<FieldMessage> list = new ArrayList<>();

			// TODO all account types update validation
			
			return list.isEmpty();
		}
		
		return true;
	}
}
