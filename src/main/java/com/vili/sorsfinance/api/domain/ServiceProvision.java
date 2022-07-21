package com.vili.sorsfinance.api.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vili.sorsfinance.api.domain.enums.AssetType;
import com.vili.sorsfinance.api.repositories.ServiceProvisionRepository;
import com.vili.sorsfinance.api.services.ServiceProvisionService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;

@Entity
@ServiceRef(ServiceProvisionService.class)
@RepositoryRef(ServiceProvisionRepository.class)
public class ServiceProvision extends Asset {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date deliveryDate;
	
	public ServiceProvision() {
		super();
	}

	public ServiceProvision(Long id, String description, AssetType type, Date deliveryDate) {
		super(id, description, type, ServiceProvision.class);
		this.deliveryDate = deliveryDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
}
