package com.vili.sorsfinance.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vili.sorsfinance.api.domain.Contact;
import com.vili.sorsfinance.api.domain.Country;
import com.vili.sorsfinance.api.domain.dto.PhoneDTO;
import com.vili.sorsfinance.api.domain.enums.PhoneType;
import com.vili.sorsfinance.api.validation.constraints.ValidPhone;
import com.vili.sorsfinance.framework.DTOType;

public class PhoneValidator implements ConstraintValidator<ValidPhone, PhoneDTO> {

	@Override
	public void initialize(ValidPhone ann) {
	}

	@Override
	public boolean isValid(PhoneDTO dto, ConstraintValidatorContext context) {
		Validator validator = new Validator();
		
		if (dto.getMethod().equals(DTOType.INSERT)) {
			validator.notEmpty("number", dto.getNumber(), true);
			validator.notEmpty("preferred", dto.getPreferred(), true);
			validator.enumValue("type", dto.getType(), PhoneType.class, true);
			validator.entityId("countryId", dto.getCountryId(), Country.class, true);
			validator.entityId("contactId", dto.getContactId(), Contact.class, true);
		} else {
			
		}

		return validator.validate(context);
	}
}
