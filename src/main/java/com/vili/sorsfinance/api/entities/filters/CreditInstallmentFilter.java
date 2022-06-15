package com.vili.sorsfinance.api.entities.filters;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vili.sorsfinance.api.entities.CreditInstallment;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.CreditInstallmentRepository;

public class CreditInstallmentFilter extends EntityFilter<CreditInstallment> {

	public CreditInstallmentFilter(Request request, CreditInstallmentRepository repository) {
		super(request, repository);
	}

	@Override
	public Page<CreditInstallment> apply() {
		Map<String, ?> filter = getFilter();
		Pageable pageable = getPageRequest();
		CreditInstallmentRepository repository = (CreditInstallmentRepository) getRepository();
		
		if (filter != null) {
			boolean hasStatus = filter.get("status") != null;

			if (hasStatus) {
				Integer status = (Integer) filter.get("status");
				return repository.findByStatus(status, pageable);
			}
		}

		return repository.findAll(pageable);
	}
}
