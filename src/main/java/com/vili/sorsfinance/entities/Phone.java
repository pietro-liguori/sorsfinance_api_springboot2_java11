package com.vili.sorsfinance.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.vili.sorsfinance.entities.enums.PhoneType;

@Entity
public class Phone implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String number;
	private Integer type;
	private Boolean preferred;

	
	public Phone() {
		
	}

	public Phone(Long id, String number, PhoneType type, Boolean preferred) {
		super();
		this.id = id;
		this.number = number;
		this.type = type.getCode();
		this.preferred = preferred;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public PhoneType getType() {
		return PhoneType.toEnum(type);
	}

	public void setType(PhoneType type) {
		this.type = type.getCode();
	}

	public Boolean getPreferred() {
		return preferred;
	}

	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phone other = (Phone) obj;
		return Objects.equals(id, other.id);
	}
}
