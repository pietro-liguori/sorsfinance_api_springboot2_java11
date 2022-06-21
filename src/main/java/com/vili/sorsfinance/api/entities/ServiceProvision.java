package com.vili.sorsfinance.api.entities;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vili.sorsfinance.api.entities.dto.AssetDTO;
import com.vili.sorsfinance.api.entities.enums.AssetType;
import com.vili.sorsfinance.api.framework.DTOType;

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

	public ServiceProvision setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
		return this;
	}
	
	public static ServiceProvision fromDTO(AssetDTO dto) {
		ServiceProvision obj = new ServiceProvision(dto.getId(), dto.getName(), AssetType.toEnum(dto.getType()), dto.getDeliveryDate());

		for (Long categoryId : dto.getCategoryIds()) {
			obj.addCategory(new Category(categoryId));
		}

		if (dto.getMethod().equals(DTOType.INSERT))
			obj.setCreatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));

		obj.setUpdatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));

		return obj;
	}
}
