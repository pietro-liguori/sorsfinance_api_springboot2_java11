package com.vili.sorsfinance.api.repositories;

import java.util.Optional;

import com.vili.sorsfinance.api.domain.CreditCardStatement;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(CreditCardStatement.class)
public interface CreditCardStatementRepository extends IRepository<CreditCardStatement> {

	Optional<CreditCardStatement> findByAccountIdAndDescription(Long account, String description);
}
