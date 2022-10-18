package com.vili.sorsfinance.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.vili.sorsfinance.api.domain.Branch;
import com.vili.sorsfinance.api.domain.Person;
import com.vili.sorsfinance.api.domain.dto.PersonDTO;
import com.vili.sorsfinance.api.domain.enums.PersonProfile;
import com.vili.sorsfinance.api.domain.enums.PersonType;
import com.vili.sorsfinance.api.repositories.PersonRepository;
import com.vili.sorsfinance.api.validation.constraints.ValidPerson;
import com.vili.sorsfinance.framework.DTOType;

public class PersonValidator implements ConstraintValidator<ValidPerson, PersonDTO> {

	@Autowired
	PersonRepository repo;

	private String locale;

	@Override
	public void initialize(ValidPerson ann) {
		this.locale = "BR";
	}

	@Override
	public boolean isValid(PersonDTO dto, ConstraintValidatorContext context) {
		Validator validator = new Validator();

		if (dto.getMethod().equals(DTOType.INSERT)) {
			validator.notEmpty("name", dto.getName(), true);
			validator.length("name", dto.getName(), 3, 120, true);

			if (validator.enumValue("profile", dto.getProfile(), PersonProfile.class, true) & validator.enumValue("type", dto.getType(), PersonType.class, true)) {
				boolean isHolder = PersonProfile.toEnum(dto.getProfile()).equals(PersonProfile.HOLDER);
				
				if (validator.enumValue("type", dto.getType(), PersonType.class, true)) {
					PersonType type = PersonType.toEnum(dto.getType());
					
					if (validator.socialId(dto.getSocialId(), type, getLocale(), isHolder)) {
						Person probe = new Person();
						probe.setType(type);
						probe.setSocialId(dto.getSocialId());
						validator.unique("socialId", Example.of(probe), true);
					}
					
					if (type.equals(PersonType.LEGAL_PERSON)) {
						validator.entityId("branchId", dto.getBranchId(), Branch.class, true);
					}
					
					if (isHolder) {
						switch (type) {
						case LEGAL_PERSON:
							validator.notEmpty("foundationDate", dto.getFoundationDate(), true);
							break;
						case NATURAL_PERSON:
							validator.notEmpty("birthDate", dto.getBirthDate(), true);
							break;
						}
					}
				}
			}
		} else {
			
		}

		return validator.validate(context);
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
}
