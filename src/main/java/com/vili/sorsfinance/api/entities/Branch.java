package com.vili.sorsfinance.api.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vili.sorsfinance.api.framework.BusEntity;

@Entity
public class Branch extends BusEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	@JsonIgnore
	@OneToMany(mappedBy = "branch")
	private Set<Person> people = new HashSet<>();
	
	public Branch() {
		super();
	}

	public Branch(Long id, String name) {
		super(id, Branch.class);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonIgnore
	public Set<Person> getPeople() {
		return people;
	}
}
