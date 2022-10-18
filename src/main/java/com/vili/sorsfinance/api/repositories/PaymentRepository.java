package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.Payment;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(Payment.class)
public interface PaymentRepository extends IRepository<Payment> {

}
