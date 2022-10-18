package com.vili.sorsfinance.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.domain.Person;
import com.vili.sorsfinance.api.domain.dto.ContactDTO;
import com.vili.sorsfinance.api.domain.enums.ContactType;
import com.vili.sorsfinance.api.repositories.PersonRepository;
import com.vili.sorsfinance.api.validation.constraints.ValidContact;
import com.vili.sorsfinance.framework.DTOType;
import com.vili.sorsfinance.framework.exceptions.FieldMessage;

public class ContactValidator implements ConstraintValidator<ValidContact, ContactDTO> {

	@Autowired
	PersonRepository personRepo;

	@Override
	public void initialize(ValidContact ann) {
	}

	@Override
	public boolean isValid(ContactDTO dto, ConstraintValidatorContext context) {
		Validator validator = new Validator();

		if (dto.getMethod().equals(DTOType.INSERT)) {
			Person owner = (Person) validator.entityId("ownerId", dto.getOwnerId(), Person.class, true);
			
			if (owner != null) {				
				if (owner.getContact() != null)
					validator.addError(new FieldMessage("ownerId", "Referenced owner [id=" + dto.getOwnerId() + "] already has a contact [id=" + owner.getContact().getId() + "]. Update owner's contact data instead"));
			}
			
			validator.enumValue("preferredContact", dto.getPreferredContact(), ContactType.class, true);
		} else {
			
		}

		return validator.validate(context);
	}
}
