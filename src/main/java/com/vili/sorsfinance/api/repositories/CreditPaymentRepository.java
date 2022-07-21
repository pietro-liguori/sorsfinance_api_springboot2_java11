package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.CreditPayment;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IRepository;

@EntityRef(CreditPayment.class)
public interface CreditPaymentRepository extends IRepository<CreditPayment> {

}
