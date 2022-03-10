package com.vili.sorsfinance.entities;

import java.util.Date;

import javax.persistence.Entity;

import com.vili.sorsfinance.entities.enums.AssetType;

@Entity
public class ServiceProvision extends Asset {

	private static final long serialVersionUID = 1L;

	private Date deliveryDate;
	
	public ServiceProvision() {
	}

	public ServiceProvision(Long id, String description, AssetType type, Category category, Date deliveryDate) {
		super(id, description, type, category);
		this.deliveryDate = deliveryDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
}
