package com.vili.sorsfinance.api.domain.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.vili.sorsfinance.api.domain.Person;
import com.vili.sorsfinance.api.domain.enums.PersonProfile;
import com.vili.sorsfinance.api.domain.enums.PersonType;
import com.vili.sorsfinance.api.validation.constraints.ValidBranchId;
import com.vili.sorsfinance.api.validation.constraints.ValidEnumValue;
import com.vili.sorsfinance.api.validation.constraints.ValidPerson;
import com.vili.sorsfinance.framework.DataTransferObject;

@ValidPerson
public class PersonDTO extends DataTransferObject {

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
	
	@Override
	public Person toEntity() {
		return new Person(getId(), getName(), getSocialId(), PersonType.toEnum(getType()), PersonProfile.toEnum(getProfile()));
	}
}
