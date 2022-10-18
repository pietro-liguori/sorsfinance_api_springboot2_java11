package com.vili.sorsfinance.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vili.sorsfinance.api.domain.Contact;
import com.vili.sorsfinance.api.domain.dto.EmailDTO;
import com.vili.sorsfinance.api.validation.constraints.ValidEmail;
import com.vili.sorsfinance.framework.DTOType;

public class EmailValidator implements ConstraintValidator<ValidEmail, EmailDTO> {

	@Override
	public void initialize(ValidEmail ann) {
	}

	@Override
	public boolean isValid(EmailDTO dto, ConstraintValidatorContext context) {
		Validator validator = new Validator();
		
		if (dto.getMethod().equals(DTOType.INSERT)) {
			validator.email(dto.getName(), true);
			validator.notEmpty("preferred", dto.getPreferred(), true);
			validator.entityId("contactId", dto.getContactId(), Contact.class, true);
		} else {
			
		}

		return validator.validate(context);
	}
}
