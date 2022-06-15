package com.vili.sorsfinance.api.entities;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vili.sorsfinance.api.entities.enums.AssetType;

@Entity
public class ServiceProvision extends Asset {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date deliveryDate;
	
	public ServiceProvision() {
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
