package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.BankCard;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(BankCard.class)
public interface BankCardRepository extends IRepository<BankCard> {

}
