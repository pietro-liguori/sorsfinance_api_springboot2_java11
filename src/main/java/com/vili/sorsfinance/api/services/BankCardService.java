package com.vili.sorsfinance.api.services;

import org.springframework.stereotype.Service;

import com.vili.sorsfinance.api.domain.BankCard;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.services.IService;

@Service
@EntityRef(BankCard.class)
public class BankCardService implements IService {

}
