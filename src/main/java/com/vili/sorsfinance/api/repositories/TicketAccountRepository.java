package com.vili.sorsfinance.api.repositories;

import java.util.List;

import com.vili.sorsfinance.api.domain.TicketAccount;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IRepository;

@EntityRef(TicketAccount.class)
public interface TicketAccountRepository extends IRepository<TicketAccount> {

	List<TicketAccount> findByCardsId(Long card);
}
