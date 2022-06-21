package com.vili.sorsfinance.api.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vili.sorsfinance.api.entities.dto.BranchDTO;
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

	public Branch setName(String name) {
		this.name = name;
		return this;
	}
	
	@JsonIgnore
	public Set<Person> getPeople() {
		return people;
	}
	
	public static Branch fromDTO(BranchDTO dto) {
		Branch branch = new Branch(dto.getId(), dto.getName());
		branch.setUpdatedAt(new java.sql.Date(new Date().toInstant().toEpochMilli()));
		return branch;
	}
}
