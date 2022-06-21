package com.vili.sorsfinance.api.entities.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.vili.sorsfinance.api.entities.Person;
import com.vili.sorsfinance.api.entities.enums.PersonProfile;
import com.vili.sorsfinance.api.entities.enums.PersonType;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.validation.constraints.ValidBranchId;
import com.vili.sorsfinance.validation.constraints.ValidEnumValue;
import com.vili.sorsfinance.validation.constraints.ValidPerson;

@ValidPerson
public class PersonDTO extends DTO<Person> {

	@NotBlank(message = "Must not be null or empty")
	@Length(min = 3, max = 120, message = "Must be between 3 and 120 characters")
	private String name;
	private String socialId;
	@ValidEnumValue(target = PersonType.class)
	private Integer type;
	@ValidEnumValue(target = PersonProfile.class)
	private Integer profile;
	@ValidBranchId
	private Long branchId;

	public PersonDTO() {
		super();
	}

	public Long getBranchId() {
		return branchId;
	}

	public PersonDTO setBranchId(Long accountId) {
		this.branchId = accountId;
		return this;
	}

	public String getName() {
		return name;
	}

	public PersonDTO setName(String name) {
		this.name = name;
		return this;
	}

	public Integer getType() {
		return type;
	}

	public PersonDTO setType(Integer type) {
		this.type = type;
		return this;
	}

	public Integer getProfile() {
		return profile;
	}

	public PersonDTO setProfile(Integer profile) {
		this.profile = profile;
		return this;
	}

	public String getSocialId() {
		return socialId;
	}

	public PersonDTO setSocialId(String socialId) {
		this.socialId = socialId;
		return this;
	}
}
