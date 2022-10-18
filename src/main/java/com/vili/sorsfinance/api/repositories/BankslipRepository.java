package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.Bankslip;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(Bankslip.class)
public interface BankslipRepository extends IRepository<Bankslip> {

}
