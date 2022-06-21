package com.vili.sorsfinance.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.entities.Category;
import com.vili.sorsfinance.api.repositories.CategoryRepository;
import com.vili.sorsfinance.validation.constraints.ValidCategoryId;

public class CategoryIdValidator implements ConstraintValidator<ValidCategoryId, Long> {

	@Autowired
	CategoryRepository repo;

	@Override
	public void initialize(ValidCategoryId ann) {
	}

	@Override
	public boolean isValid(Long id, ConstraintValidatorContext context) {
		List<String> list = new ArrayList<>();

		if (id != null) {
			Optional<Category> aux = repo.findById(id);

			if (aux.isEmpty())
				list.add("Category not found: " + id);
		}

		for (String msg : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
		}

		return list.isEmpty();
	}
}
