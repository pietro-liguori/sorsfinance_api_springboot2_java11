package com.vili.sorsfinance.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vili.sorsfinance.api.domain.BankAccount;
import com.vili.sorsfinance.api.domain.CreditCardStatement;
import com.vili.sorsfinance.api.repositories.CreditCardStatementRepository;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.services.IService;

@Service
@EntityRef(CreditCardStatement.class)
public class CreditCardStatementService implements IService {
	
	@Autowired
	CreditCardStatementRepository repo;

	public CreditCardStatement findStatement(BankAccount account, String description) {
		Optional<CreditCardStatement> aux = repo.findByAccountIdAndDescription(account.getId(), description);

		if (aux.isEmpty())
			return null;
		else
			return aux.get();
	}
}
