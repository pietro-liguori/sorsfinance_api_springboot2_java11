package com.vili.sorsfinance.api.domain;

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
	private String description;
	
	public ServiceProvision() {
		super();
		super.setType(AssetType.SERVICE_PROVISION);
	}
	
	public ServiceProvision(Long id) {
		super(id, ServiceProvision.class);
		super.setType(AssetType.SERVICE_PROVISION);
	}

	public ServiceProvision(Long id, String name, String description) {
		super(id, name, AssetType.SERVICE_PROVISION, ServiceProvision.class);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
