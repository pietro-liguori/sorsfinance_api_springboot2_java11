package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.CreditCardStatement;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IRepository;

@EntityRef(CreditCardStatement.class)
public interface CreditCardStatementRepository extends IRepository<CreditCardStatement> {

}
