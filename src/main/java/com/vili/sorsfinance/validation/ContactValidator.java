package com.vili.sorsfinance.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.entities.Person;
import com.vili.sorsfinance.api.entities.dto.ContactDTO;
import com.vili.sorsfinance.api.framework.DTOType;
import com.vili.sorsfinance.api.framework.FieldMessage;
import com.vili.sorsfinance.api.repositories.AddressRepository;
import com.vili.sorsfinance.api.repositories.EmailRepository;
import com.vili.sorsfinance.api.repositories.PersonRepository;
import com.vili.sorsfinance.api.repositories.PhoneRepository;
import com.vili.sorsfinance.validation.constraints.ValidContact;

public class ContactValidator implements ConstraintValidator<ValidContact, ContactDTO> {

	@Autowired
	AddressRepository addressRepo;
	@Autowired
	EmailRepository emailRepo;
	@Autowired
	PersonRepository personRepo;
	@Autowired
	PhoneRepository phoneRepo;

	@Override
	public void initialize(ValidContact ann) {

	}

	@Override
	public boolean isValid(ContactDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (dto.getMethod() == DTOType.INSERT) {
			if (dto.getOwnerId() == null)
				list.add(new FieldMessage("ownerId", "Must not be null"));
			else {
				Optional<Person> aux = personRepo.findById(dto.getOwnerId());

				if (aux.isEmpty())
					list.add(new FieldMessage("ownerId", "Resource not found: " + dto.getOwnerId()));
				else {
					if (aux.get().getContact() != null)
						list.add(new FieldMessage("ownerId",
								"Referenced owner already has a contact. Update owner's contact data instead"));
				}
			}
		} else if (dto.getMethod() == DTOType.UPDATE) {
			// TODO all contact types update validation
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
					.addConstraintViolation();
		}

		return list.isEmpty();
	}
}
