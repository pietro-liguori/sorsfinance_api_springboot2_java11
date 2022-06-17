package com.vili.sorsfinance.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.entities.Person;
import com.vili.sorsfinance.api.entities.enums.PersonProfile;
import com.vili.sorsfinance.api.repositories.PersonRepository;
import com.vili.sorsfinance.validation.constraints.ValidPersonId;

public class PersonIdValidator implements ConstraintValidator<ValidPersonId, Long> {

	@Autowired
	PersonRepository repo;

	private PersonProfile profile;
	private boolean acceptAll;

	@Override
	public void initialize(ValidPersonId ann) {
		profile = ann.profile();
		acceptAll = ann.acceptAll();
	}

	@Override
	public boolean isValid(Long id, ConstraintValidatorContext context) {
		List<String> list = new ArrayList<>();

		if (id == null) {
			list.add("Must not be null");
		} else {
			Optional<Person> aux = repo.findById(id);

			if (aux.isEmpty())
				list.add("Resource not found: " + id);
			else {
				boolean isApplicable = profile != PersonProfile.STANDARD && !acceptAll;
				if (isApplicable && !aux.get().getProfile().equals(profile.getLabel()))
					list.add("Must reference to a person with " + profile + " profile");
			}
		}

		for (String msg : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
		}

		return list.isEmpty();
	}

	public PersonProfile getProfile() {
		return profile;
	}

	public void setProfile(PersonProfile profile) {
		this.profile = profile;
	}

	public boolean isAcceptAll() {
		return acceptAll;
	}

	public void setAcceptAll(boolean acceptAll) {
		this.acceptAll = acceptAll;
	}
}
