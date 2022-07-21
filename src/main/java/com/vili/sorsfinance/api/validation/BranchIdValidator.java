package com.vili.sorsfinance.api.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.domain.Branch;
import com.vili.sorsfinance.api.repositories.BranchRepository;
import com.vili.sorsfinance.api.validation.constraints.ValidBranchId;

public class BranchIdValidator implements ConstraintValidator<ValidBranchId, Long> {

	@Autowired
	BranchRepository repo;

	@Override
	public void initialize(ValidBranchId ann) {
	}

	@Override
	public boolean isValid(Long id, ConstraintValidatorContext context) {
		List<String> list = new ArrayList<>();

		if (id != null) {
			Optional<Branch> aux = repo.findById(id);

			if (aux.isEmpty())
				list.add("Branch not found: " + id);
		}

		for (String msg : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
		}

		return list.isEmpty();
	}
}
