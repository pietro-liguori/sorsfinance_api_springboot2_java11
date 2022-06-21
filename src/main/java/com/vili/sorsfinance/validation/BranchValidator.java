package com.vili.sorsfinance.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.repositories.BranchRepository;
import com.vili.sorsfinance.validation.constraints.ValidBranch;

public class BranchValidator implements ConstraintValidator<ValidBranch, String> {

	@Autowired
	BranchRepository repo;

	@Override
	public void initialize(ValidBranch ann) {
	}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		List<String> list = new ArrayList<>();

		if (name != null) {
			if (!repo.findByNameIgnoreCase(name).isEmpty())
				list.add("Branch '" + name + "' already exists");
		}

		for (String msg : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
		}

		return list.isEmpty();
	}
}
