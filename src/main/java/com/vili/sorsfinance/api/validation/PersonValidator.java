package com.vili.sorsfinance.api.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.vili.sorsfinance.api.domain.Person;
import com.vili.sorsfinance.api.domain.dto.PersonDTO;
import com.vili.sorsfinance.api.domain.enums.PersonProfile;
import com.vili.sorsfinance.api.domain.enums.PersonType;
import com.vili.sorsfinance.api.repositories.PersonRepository;
import com.vili.sorsfinance.api.validation.constraints.ValidPerson;
import com.vili.sorsfinance.api.validation.utils.BR;
import com.vili.sorsfinance.framework.FieldMessage;
import com.vili.sorsfinance.framework.exceptions.EnumValueNotFoundException;

public class PersonValidator implements ConstraintValidator<ValidPerson, PersonDTO> {

	@Autowired
	PersonRepository repo;

	private String locale;

	@Override
	public void initialize(ValidPerson ann) {
		this.locale = ann.locale();
	}

	@Override
	public boolean isValid(PersonDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (dto.getProfile() != null) {
			try {
				boolean isHolder = PersonProfile.toEnum(dto.getProfile()).equals(PersonProfile.HOLDER);

				if (dto.getSocialId() != null) {
					Example<Person> ex = Example.of(new Person(null, null, dto.getSocialId(), null, null));
					List<Person> aux = repo.findAll(ex);

					if (!aux.isEmpty())
						list.add(new FieldMessage("socialId", "SocialId '" + dto.getSocialId()
								+ "' belongs to another person(id=" + aux.get(0).getId() + ")"));
					else {
						switch (locale) {
						case "BR":
							if (dto.getType() != null) {
								PersonType type = PersonType.toEnum(dto.getType());
								if (type.equals(PersonType.NATURAL_PERSON)) {
									if (!BR.isValidCPF(dto.getSocialId()))
										list.add(new FieldMessage("socialId",
												"Invalid brazilian natural person socialId (CPF)"));
								}

								if (type.equals(PersonType.LEGAL_PERSON)) {
									if (!BR.isValidCNPJ(dto.getSocialId()))
										list.add(new FieldMessage("socialId",
												"Invalid brazilian legal person socialId (CNPJ)"));
								}
							}
							break;
						default:
							break;
						}
					}
				} else {
					if (isHolder)
						list.add(new FieldMessage("socialId", "Holder's socialId must not be null"));
				}
			} catch (EnumValueNotFoundException e) {
			}
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
					.addConstraintViolation();
		}

		return list.isEmpty();
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
}
