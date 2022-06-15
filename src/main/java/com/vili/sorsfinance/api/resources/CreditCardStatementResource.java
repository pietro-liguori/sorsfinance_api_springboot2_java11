package com.vili.sorsfinance.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.entities.CreditCardStatement;
import com.vili.sorsfinance.api.entities.filters.CreditCardStatementFilter;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.CreditCardStatementRepository;

@RestController
@RequestMapping(value = "/creditcardstatements")
public class CreditCardStatementResource extends DefaultResource<CreditCardStatement, DTO<CreditCardStatement>> {

	@Autowired 
	CreditCardStatementRepository repo;

	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<CreditCardStatement>> U getFilter() {
		return (U) new CreditCardStatementFilter(Request.from(getRequest()), repo);
	}
}
