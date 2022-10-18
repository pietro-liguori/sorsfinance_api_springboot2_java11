package com.vili.sorsfinance.api.services;

import org.springframework.stereotype.Service;

import com.vili.sorsfinance.api.domain.BankAccount;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.services.IService;

@Service
@EntityRef(BankAccount.class)
public class BankAccountService implements IService {

}
