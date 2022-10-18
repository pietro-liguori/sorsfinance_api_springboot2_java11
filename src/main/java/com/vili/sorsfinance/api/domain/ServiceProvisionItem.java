package com.vili.sorsfinance.api.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vili.sorsfinance.api.domain.enums.TransactionItemType;
import com.vili.sorsfinance.api.repositories.ServiceProvisionItemRepository;
import com.vili.sorsfinance.api.services.ServiceProvisionItemService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.request.annotations.FilterSetting;

@Entity
@ServiceRef(ServiceProvisionItemService.class)
@RepositoryRef(ServiceProvisionItemRepository.class)
public class ServiceProvisionItem extends TransactionItem {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "provider_id")
	@FilterSetting(nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Person provider;

	public ServiceProvisionItem() {
		super();
		super.setType(TransactionItemType.SERVICE_PROVISION_ITEM);
	}
	
	public ServiceProvisionItem(Long id) {
		super(id, ServiceProvisionItem.class);
		super.setType(TransactionItemType.SERVICE_PROVISION_ITEM);
	}
	
	public ServiceProvisionItem(Long id, Transaction transaction, Asset asset, Double price, Integer quantity, Double discount, Date deliveryDate, Person provider) {
		super(id, transaction, asset, TransactionItemType.SERVICE_PROVISION_ITEM, price, quantity, discount, deliveryDate, ProductItem.class);
		this.provider = provider;
	}
	
	public Person getProvider() {
		return provider;
	}

	public void setProvider(Person provider) {
		this.provider = provider;
	}
}
