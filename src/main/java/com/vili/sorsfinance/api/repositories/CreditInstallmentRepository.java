package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.CreditInstallment;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IRepository;

@EntityRef(CreditInstallment.class)
public interface CreditInstallmentRepository extends IRepository<CreditInstallment> {

}
