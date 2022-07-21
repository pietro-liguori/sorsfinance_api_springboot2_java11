package com.vili.sorsfinance.api.repositories;

import java.util.List;

import com.vili.sorsfinance.api.domain.BankAccount;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IRepository;

@EntityRef(BankAccount.class)
public interface BankAccountRepository extends IRepository<BankAccount> {

	List<BankAccount> findByCardsId(Long card);
}
