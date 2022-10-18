package com.vili.sorsfinance.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vili.sorsfinance.api.domain.City;
import com.vili.sorsfinance.api.domain.Contact;
import com.vili.sorsfinance.api.domain.dto.AddressDTO;
import com.vili.sorsfinance.api.domain.enums.AddressType;
import com.vili.sorsfinance.api.validation.constraints.ValidAddress;
import com.vili.sorsfinance.framework.DTOType;

public class AddressValidator implements ConstraintValidator<ValidAddress, AddressDTO> {

	@Override
	public void initialize(ValidAddress ann) {
	}

	@Override
	public boolean isValid(AddressDTO dto, ConstraintValidatorContext context) {
		Validator validator = new Validator();
		
		if (dto.getMethod().equals(DTOType.INSERT)) {
			validator.notEmpty("address", dto.getAddress(), true);
			validator.notEmpty("number", dto.getNumber(), true);
			validator.notEmpty("district", dto.getDistrict(), true);
			validator.notEmpty("complement", dto.getDistrict(), false);
			validator.notEmpty("zipCode", dto.getZipCode(), true);
			validator.notEmpty("preferred", dto.getPreferred(), true);
			validator.enumValue("type", dto.getType(), AddressType.class, true);
			validator.entityId("cityId", dto.getCityId(), City.class, true);
			validator.entityId("contactId", dto.getContactId(), Contact.class, true);
		} else {
			
		}

		return validator.validate(context);
	}
}
