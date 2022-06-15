package com.vili.sorsfinance.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.entities.Payment;
import com.vili.sorsfinance.api.entities.filters.PaymentFilter;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.PaymentRepository;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource extends DefaultResource<Payment, DTO<Payment>> {

	@Autowired
	PaymentRepository repo;

	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<Payment>> U getFilter() {
		return (U) new PaymentFilter(Request.from(getRequest()), repo);
	}
}
