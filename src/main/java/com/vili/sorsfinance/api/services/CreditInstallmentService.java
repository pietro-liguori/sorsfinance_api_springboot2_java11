package com.vili.sorsfinance.api.services;

import org.springframework.stereotype.Service;

import com.vili.sorsfinance.api.domain.CreditInstallment;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IService;

@Service
@EntityRef(CreditInstallment.class)
public class CreditInstallmentService implements IService {

}
