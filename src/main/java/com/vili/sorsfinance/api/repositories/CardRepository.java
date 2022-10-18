package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.Card;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(Card.class)
public interface CardRepository extends IRepository<Card> {

}
