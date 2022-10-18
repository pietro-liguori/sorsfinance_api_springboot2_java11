package com.vili.sorsfinance.api.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vili.sorsfinance.api.domain.enums.CadastralStatus;
import com.vili.sorsfinance.api.domain.enums.PersonProfile;
import com.vili.sorsfinance.api.domain.enums.PersonType;
import com.vili.sorsfinance.api.repositories.LegalPersonRepository;
import com.vili.sorsfinance.api.services.LegalPersonService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.request.annotations.FilterSetting;

@Entity
@ServiceRef(value = LegalPersonService.class)
@RepositoryRef(value = LegalPersonRepository.class)
public class LegalPerson extends Person {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String tradeName;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date foundationDate;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name = "branch_id")
	@FilterSetting(nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Branch branch;

	public LegalPerson() {
		super();
		super.setType(PersonType.LEGAL_PERSON);
	}
	
	public LegalPerson(Long id) {
		super(id, LegalPerson.class);
		super.setType(PersonType.LEGAL_PERSON);
	}

	public LegalPerson(Long id, String name, String tradeName, String socialId, Date foundationDate, CadastralStatus status, PersonType type, PersonProfile profile) {
		super(id, name, socialId, PersonType.LEGAL_PERSON, profile, LegalPerson.class);
		this.tradeName = tradeName;
		this.foundationDate = foundationDate;
		this.status = status == null ? null : status.getCode();
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public Date getFoundationDate() {
		return foundationDate;
	}

	public void setFoundationDate(Date foundationDate) {
		this.foundationDate = foundationDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(CadastralStatus status) {
		this.status = status == null ? null : status.getCode();
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
}
