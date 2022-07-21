package com.vili.sorsfinance.api.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.domain.Transaction;
import com.vili.sorsfinance.api.repositories.TransactionRepository;
import com.vili.sorsfinance.api.validation.constraints.ValidTransactionId;

public class TransactionIdValidator implements ConstraintValidator<ValidTransactionId, Long> {

	@Autowired
	TransactionRepository repo;

	@Override
	public void initialize(ValidTransactionId ann) {
	}

	@Override
	public boolean isValid(Long id, ConstraintValidatorContext context) {
		List<String> list = new ArrayList<>();

		if (id != null) {
			Optional<Transaction> aux = repo.findById(id);

			if (aux.isEmpty())
				list.add("Transaction not found: " + id);
		}

		for (String msg : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
		}

		return list.isEmpty();
	}
}
