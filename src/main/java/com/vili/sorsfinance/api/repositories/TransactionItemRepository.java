package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.TransactionItem;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IRepository;

@EntityRef(TransactionItem.class)
public interface TransactionItemRepository extends IRepository<TransactionItem> {

}
