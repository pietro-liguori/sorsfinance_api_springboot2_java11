package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.Transaction;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(Transaction.class)
public interface TransactionRepository extends IRepository<Transaction> {

}
