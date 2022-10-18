package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.VoucherAccount;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(VoucherAccount.class)
public interface VoucherAccountRepository extends IRepository<VoucherAccount> {

}
