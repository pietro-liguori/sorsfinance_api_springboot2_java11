package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.Account;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IRepository;

@EntityRef(Account.class)
public interface AccountRepository extends IRepository<Account> {

}
