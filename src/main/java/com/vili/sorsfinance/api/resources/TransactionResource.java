package com.vili.sorsfinance.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.entities.Transaction;
import com.vili.sorsfinance.api.entities.filters.TransactionFilter;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.TransactionRepository;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionResource extends DefaultResource<Transaction, DTO<Transaction>>{

	@Autowired 
	TransactionRepository repo;

	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<Transaction>> U getFilter() {
		return (U) new TransactionFilter(Request.from(getRequest()), repo);
	}
}
