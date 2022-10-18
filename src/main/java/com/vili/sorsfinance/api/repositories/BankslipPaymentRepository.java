package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.BankslipPayment;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(BankslipPayment.class)
public interface BankslipPaymentRepository extends IRepository<BankslipPayment> {

}
