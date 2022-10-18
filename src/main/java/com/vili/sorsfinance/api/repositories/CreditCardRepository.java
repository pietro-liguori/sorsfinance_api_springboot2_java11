package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.CreditCard;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(CreditCard.class)
public interface CreditCardRepository extends IRepository<CreditCard> {

}
