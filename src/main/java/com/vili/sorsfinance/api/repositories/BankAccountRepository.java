package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.BankAccount;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(BankAccount.class)
public interface BankAccountRepository extends IRepository<BankAccount> {

}
