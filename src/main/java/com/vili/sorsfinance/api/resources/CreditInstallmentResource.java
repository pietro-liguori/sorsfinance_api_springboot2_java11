package com.vili.sorsfinance.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.entities.CreditInstallment;
import com.vili.sorsfinance.api.entities.filters.CreditInstallmentFilter;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.CreditInstallmentRepository;

@RestController
@RequestMapping(value = "/creditinstallments")
public class CreditInstallmentResource extends DefaultResource<CreditInstallment, DTO<CreditInstallment>> {

	@Autowired 
	CreditInstallmentRepository repo;

	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<CreditInstallment>> U getFilter() {
		return (U) new CreditInstallmentFilter(Request.from(getRequest()), repo);
	}
}
