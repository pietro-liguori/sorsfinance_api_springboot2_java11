package com.vili.sorsfinance.api.services;

import org.springframework.stereotype.Service;

import com.vili.sorsfinance.api.domain.CreditCard;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.services.IService;

@Service
@EntityRef(CreditCard.class)
public class CreditCardService implements IService {

}
