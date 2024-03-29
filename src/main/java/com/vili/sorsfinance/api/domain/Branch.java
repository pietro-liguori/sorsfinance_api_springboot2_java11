package com.vili.sorsfinance.api.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vili.sorsfinance.api.repositories.BranchRepository;
import com.vili.sorsfinance.api.services.BranchService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.request.annotations.NoFilter;

@Entity
@ServiceRef(value = BranchService.class)
@RepositoryRef(value = BranchRepository.class)
public class Branch extends BusinessEntity {
	
	private static final long serialVersionUID = 1L;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;
	
	@OneToMany(mappedBy = "branch")
	@NoFilter
	@JsonIgnore
	private Set<LegalPerson> people = new HashSet<>();
	
	public Branch() {
		super();
	}

	public Branch(Long id) {
		super(id, Branch.class);
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
	
	public List<LegalPerson> getPeople() {
		return people.stream().toList();
	}
}
