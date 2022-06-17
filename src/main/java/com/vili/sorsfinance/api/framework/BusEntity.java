package com.vili.sorsfinance.api.framework;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "entities")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BusEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnore
	private Class<?> sorsClass;
	@JsonIgnore
	private Date createdAt;
	@JsonIgnore
	private Date updatedAt;
	@JsonIgnore
	private boolean active;
	
	public BusEntity() {
		super();
		this.active = true;
	}

	public BusEntity(Long id, Class<?> sorsClass) {
		super();
		this.id = id;
		this.sorsClass = sorsClass;
		this.active = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Class<?> getSorsClass() {
		return sorsClass;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date date) {
		this.createdAt = date;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date date) {
		this.updatedAt = date;
	}

	public boolean isActive() {
		return active;
	}

	public void setAsActive() {
		this.active = true;
	}
	
	public void setAsInactive() {
		this.active = false;
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
		BusEntity other = (BusEntity) obj;
		return Objects.equals(id, other.id);
	}
	
	public static <T extends BusEntity> T fromDTO(DTO<T> dto) {
		return null;
	}
}
