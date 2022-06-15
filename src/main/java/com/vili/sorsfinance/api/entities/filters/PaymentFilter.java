package com.vili.sorsfinance.api.entities.filters;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vili.sorsfinance.api.entities.Payment;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.PaymentRepository;

public class PaymentFilter extends EntityFilter<Payment> {

	public PaymentFilter(Request request, PaymentRepository repository) {
		super(request, repository);
	}

	@Override
	public Page<Payment> apply() {
		Map<String, ?> filter = getFilter();
		Pageable pageable = getPageRequest();
		PaymentRepository repository = (PaymentRepository) getRepository();
		
		if (filter != null) {
			boolean hasCard = filter.get("card") != null;
			boolean hasAccount = filter.get("account") != null;
			boolean hasResponsible = filter.get("responsible") != null;
			boolean hasTransaction = filter.get("transaction") != null;
			boolean hasType = filter.get("type") != null;
			boolean hasStatus = filter.get("status") != null;

			if (!hasCard) {
				if (!hasAccount) {
					if (hasResponsible && hasTransaction && hasType && hasStatus) {
						Long responsible = (Long) filter.get("responsible");
						Long transaction = (Long) filter.get("transaction");
						Integer type = (Integer) filter.get("type");
						Integer status = (Integer) filter.get("status");
						return repository.findByResponsibleIdAndTransactionIdAndTypeAndStatus(responsible, transaction, type, status, pageable);
					}

					if (hasResponsible && hasTransaction && hasType && !hasStatus) {
						Long responsible = (Long) filter.get("responsible");
						Long transaction = (Long) filter.get("transaction");
						Integer type = (Integer) filter.get("type");
						return repository.findByResponsibleIdAndTransactionIdAndType(responsible, transaction, type, pageable);
					}
					
					if (hasResponsible && hasTransaction && !hasType && hasStatus) {
						Long responsible = (Long) filter.get("responsible");
						Long transaction = (Long) filter.get("transaction");
						Integer status = (Integer) filter.get("status");
						return repository.findByResponsibleIdAndTransactionIdAndStatus(responsible, transaction, status, pageable);
					}
					
					if (hasResponsible && !hasTransaction && hasType && hasStatus) {
						Long responsible = (Long) filter.get("responsible");
						Integer type = (Integer) filter.get("type");
						Integer status = (Integer) filter.get("status");
						return repository.findByResponsibleIdAndTypeAndStatus(responsible, type, status, pageable);
					}
					
					if (!hasResponsible && hasTransaction && hasType && hasStatus) {
						Long transaction = (Long) filter.get("transaction");
						Integer type = (Integer) filter.get("type");
						Integer status = (Integer) filter.get("status");
						return repository.findByTransactionIdAndTypeAndStatus(transaction, type, status, pageable);
					}

					if (hasResponsible && hasTransaction && !hasType && !hasStatus) {
						Long responsible = (Long) filter.get("responsible");
						Long transaction = (Long) filter.get("transaction");
						return repository.findByResponsibleIdAndTransactionId(responsible, transaction, pageable);
					}
					
					if (hasResponsible && !hasTransaction && hasType && !hasStatus) {
						Long responsible = (Long) filter.get("responsible");
						Integer type = (Integer) filter.get("type");
						return repository.findByResponsibleIdAndType(responsible, type, pageable);
					}
					
					if (hasResponsible && !hasTransaction && !hasType && hasStatus) {
						Long responsible = (Long) filter.get("responsible");
						Integer status = (Integer) filter.get("status");
						return repository.findByResponsibleIdAndStatus(responsible, status, pageable);
					}
					
					if (!hasResponsible && hasTransaction && hasType && !hasStatus) {
						Long transaction = (Long) filter.get("transaction");
						Integer type = (Integer) filter.get("type");
						return repository.findByTransactionIdAndType(transaction, type, pageable);
					}
					
					if (!hasResponsible && hasTransaction && !hasType && hasStatus) {
						Long transaction = (Long) filter.get("transaction");
						Integer status = (Integer) filter.get("status");
						return repository.findByTransactionIdAndStatus(transaction, status, pageable);
					}
					
					if (!hasResponsible && !hasTransaction && hasType && hasStatus) {
						Integer type = (Integer) filter.get("type");
						Integer status = (Integer) filter.get("status");
						return repository.findByTypeAndStatus(type, status, pageable);
					}

					if (hasResponsible && !hasTransaction && !hasType && !hasStatus) {
						Long responsible = (Long) filter.get("responsible");
						return repository.findByResponsibleId(responsible, pageable);
					}

					if (!hasResponsible && hasTransaction && !hasType && !hasStatus) {
						Long transaction = (Long) filter.get("transaction");
						return repository.findByTransactionId(transaction, pageable);
					}

					if (!hasResponsible && !hasTransaction && hasType && !hasStatus) {
						Integer type = (Integer) filter.get("type");
						return repository.findByType(type, pageable);
					}

					if (!hasResponsible && !hasTransaction && !hasType && hasStatus) {
						Integer status = (Integer) filter.get("status");
						return repository.findByStatus(status, pageable);
					}
				} else {
					Long account = (Long) filter.get("account");
					
					if (hasResponsible && hasTransaction && hasType && hasStatus) {
						Long responsible = (Long) filter.get("responsible");
						Long transaction = (Long) filter.get("transaction");
						Integer type = (Integer) filter.get("type");
						Integer status = (Integer) filter.get("status");
						return repository.findByAccountIdAndResponsibleIdAndTransactionIdAndTypeAndStatus(account, responsible, transaction, type, status, pageable);
					}

					if (hasResponsible && hasTransaction && hasType && !hasStatus) {
						Long responsible = (Long) filter.get("responsible");
						Long transaction = (Long) filter.get("transaction");
						Integer type = (Integer) filter.get("type");
						return repository.findByAccountIdAndResponsibleIdAndTransactionIdAndType(account, responsible, transaction, type, pageable);
					}
					
					if (hasResponsible && hasTransaction && !hasType && hasStatus) {
						Long responsible = (Long) filter.get("responsible");
						Long transaction = (Long) filter.get("transaction");
						Integer status = (Integer) filter.get("status");
						return repository.findByAccountIdAndResponsibleIdAndTransactionIdAndStatus(account, responsible, transaction, status, pageable);
					}
					
					if (hasResponsible && !hasTransaction && hasType && hasStatus) {
						Long responsible = (Long) filter.get("responsible");
						Integer type = (Integer) filter.get("type");
						Integer status = (Integer) filter.get("status");
						return repository.findByAccountIdAndResponsibleIdAndTypeAndStatus(account, responsible, type, status, pageable);
					}
					
					if (!hasResponsible && hasTransaction && hasType && hasStatus) {
						Long transaction = (Long) filter.get("transaction");
						Integer type = (Integer) filter.get("type");
						Integer status = (Integer) filter.get("status");
						return repository.findByAccountIdAndTransactionIdAndTypeAndStatus(account, transaction, type, status, pageable);
					}

					if (hasResponsible && hasTransaction && !hasType && !hasStatus) {
						Long responsible = (Long) filter.get("responsible");
						Long transaction = (Long) filter.get("transaction");
						return repository.findByAccountIdAndResponsibleIdAndTransactionId(account, responsible, transaction, pageable);
					}
					
					if (hasResponsible && !hasTransaction && hasType && !hasStatus) {
						Long responsible = (Long) filter.get("responsible");
						Integer type = (Integer) filter.get("type");
						return repository.findByAccountIdAndResponsibleIdAndType(account, responsible, type, pageable);
					}
					
					if (hasResponsible && !hasTransaction && !hasType && hasStatus) {
						Long responsible = (Long) filter.get("responsible");
						Integer status = (Integer) filter.get("status");
						return repository.findByAccountIdAndResponsibleIdAndStatus(account, responsible, status, pageable);
					}
					
					if (!hasResponsible && hasTransaction && hasType && !hasStatus) {
						Long transaction = (Long) filter.get("transaction");
						Integer type = (Integer) filter.get("type");
						return repository.findByAccountIdAndTransactionIdAndType(account, transaction, type, pageable);
					}
					
					if (!hasResponsible && hasTransaction && !hasType && hasStatus) {
						Long transaction = (Long) filter.get("transaction");
						Integer status = (Integer) filter.get("status");
						return repository.findByAccountIdAndTransactionIdAndStatus(account, transaction, status, pageable);
					}
					
					if (!hasResponsible && !hasTransaction && hasType && hasStatus) {
						Integer type = (Integer) filter.get("type");
						Integer status = (Integer) filter.get("status");
						return repository.findByAccountIdAndTypeAndStatus(account, type, status, pageable);
					}

					if (hasResponsible && !hasTransaction && !hasType && !hasStatus) {
						Long responsible = (Long) filter.get("responsible");
						return repository.findByAccountIdAndResponsibleId(account, responsible, pageable);
					}

					if (!hasResponsible && hasTransaction && !hasType && !hasStatus) {
						Long transaction = (Long) filter.get("transaction");
						return repository.findByAccountIdAndTransactionId(account, transaction, pageable);
					}

					if (!hasResponsible && !hasTransaction && hasType && !hasStatus) {
						Integer type = (Integer) filter.get("type");
						return repository.findByAccountIdAndType(account, type, pageable);
					}

					if (!hasResponsible && !hasTransaction && !hasType && hasStatus) {
						Integer status = (Integer) filter.get("status");
						return repository.findByAccountIdAndStatus(account, status, pageable);
					}

					return repository.findByAccountId(account, pageable);
				}
			} else {
				Long card = (Long) filter.get("card");

				if (hasResponsible && hasTransaction && hasType && hasStatus) {
					Long responsible = (Long) filter.get("responsible");
					Long transaction = (Long) filter.get("transaction");
					Integer type = (Integer) filter.get("type");
					Integer status = (Integer) filter.get("status");
					return repository.findByCardIdAndResponsibleIdAndTransactionIdAndTypeAndStatus(card, responsible, transaction, type, status, pageable);
				}

				if (hasResponsible && hasTransaction && hasType && !hasStatus) {
					Long responsible = (Long) filter.get("responsible");
					Long transaction = (Long) filter.get("transaction");
					Integer type = (Integer) filter.get("type");
					return repository.findByCardIdAndResponsibleIdAndTransactionIdAndType(card, responsible, transaction, type, pageable);
				}
				
				if (hasResponsible && hasTransaction && !hasType && hasStatus) {
					Long responsible = (Long) filter.get("responsible");
					Long transaction = (Long) filter.get("transaction");
					Integer status = (Integer) filter.get("status");
					return repository.findByCardIdAndResponsibleIdAndTransactionIdAndStatus(card, responsible, transaction, status, pageable);
				}
				
				if (hasResponsible && !hasTransaction && hasType && hasStatus) {
					Long responsible = (Long) filter.get("responsible");
					Integer type = (Integer) filter.get("type");
					Integer status = (Integer) filter.get("status");
					return repository.findByCardIdAndResponsibleIdAndTypeAndStatus(card, responsible, type, status, pageable);
				}
				
				if (!hasResponsible && hasTransaction && hasType && hasStatus) {
					Long transaction = (Long) filter.get("transaction");
					Integer type = (Integer) filter.get("type");
					Integer status = (Integer) filter.get("status");
					return repository.findByCardIdAndTransactionIdAndTypeAndStatus(card, transaction, type, status, pageable);
				}

				if (hasResponsible && hasTransaction && !hasType && !hasStatus) {
					Long responsible = (Long) filter.get("responsible");
					Long transaction = (Long) filter.get("transaction");
					return repository.findByCardIdAndResponsibleIdAndTransactionId(card, responsible, transaction, pageable);
				}
				
				if (hasResponsible && !hasTransaction && hasType && !hasStatus) {
					Long responsible = (Long) filter.get("responsible");
					Integer type = (Integer) filter.get("type");
					return repository.findByCardIdAndResponsibleIdAndType(card, responsible, type, pageable);
				}
				
				if (hasResponsible && !hasTransaction && !hasType && hasStatus) {
					Long responsible = (Long) filter.get("responsible");
					Integer status = (Integer) filter.get("status");
					return repository.findByCardIdAndResponsibleIdAndStatus(card, responsible, status, pageable);
				}
				
				if (!hasResponsible && hasTransaction && hasType && !hasStatus) {
					Long transaction = (Long) filter.get("transaction");
					Integer type = (Integer) filter.get("type");
					return repository.findByCardIdAndTransactionIdAndType(card, transaction, type, pageable);
				}
				
				if (!hasResponsible && hasTransaction && !hasType && hasStatus) {
					Long transaction = (Long) filter.get("transaction");
					Integer status = (Integer) filter.get("status");
					return repository.findByCardIdAndTransactionIdAndStatus(card, transaction, status, pageable);
				}
				
				if (!hasResponsible && !hasTransaction && hasType && hasStatus) {
					Integer type = (Integer) filter.get("type");
					Integer status = (Integer) filter.get("status");
					return repository.findByCardIdAndTypeAndStatus(card, type, status, pageable);
				}

				if (hasResponsible && !hasTransaction && !hasType && !hasStatus) {
					Long responsible = (Long) filter.get("responsible");
					return repository.findByCardIdAndResponsibleId(card, responsible, pageable);
				}

				if (!hasResponsible && hasTransaction && !hasType && !hasStatus) {
					Long transaction = (Long) filter.get("transaction");
					return repository.findByCardIdAndTransactionId(card, transaction, pageable);
				}

				if (!hasResponsible && !hasTransaction && hasType && !hasStatus) {
					Integer type = (Integer) filter.get("type");
					return repository.findByCardIdAndType(card, type, pageable);
				}

				if (!hasResponsible && !hasTransaction && !hasType && hasStatus) {
					Integer status = (Integer) filter.get("status");
					return repository.findByCardIdAndStatus(card, status, pageable);
				}

				return repository.findByCardId(card, pageable);
			}
		}

		return repository.findAll(pageable);
	}
}
