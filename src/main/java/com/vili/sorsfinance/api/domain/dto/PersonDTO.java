package com.vili.sorsfinance.api.domain.dto;

import java.util.Date;

import com.vili.sorsfinance.api.domain.Branch;
import com.vili.sorsfinance.api.domain.LegalPerson;
import com.vili.sorsfinance.api.domain.NaturalPerson;
import com.vili.sorsfinance.api.domain.Person;
import com.vili.sorsfinance.api.domain.enums.CadastralStatus;
import com.vili.sorsfinance.api.domain.enums.PersonProfile;
import com.vili.sorsfinance.api.domain.enums.PersonType;
import com.vili.sorsfinance.api.validation.constraints.ValidPerson;
import com.vili.sorsfinance.framework.DataTransferObject;

@ValidPerson
public class PersonDTO extends DataTransferObject {

	private String name;
	private String socialId;
	private Integer type;
	private Integer profile;
	private Date birthDate;
	private String nickname;
	private String tradeName;
	private Date foundationDate;
	private Integer status = CadastralStatus.ACTIVE.getCode();
	private Long branchId;

	public PersonDTO() {
		super();
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long accountId) {
		this.branchId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getProfile() {
		return profile;
	}

	public void setProfile(Integer profile) {
		this.profile = profile;
	}

	public String getSocialId() {
		return socialId;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public Person toEntity() {
		Person person = null;
		PersonType type = PersonType.toEnum(getType());

		if (type.equals(PersonType.NATURAL_PERSON)) {
			person = new NaturalPerson(getId(), getName(), getNickname(), getSocialId(), getBirthDate(), type, PersonProfile.toEnum(getProfile()));
		} else {
			person = new LegalPerson(getId(), getName(), getTradeName(), getSocialId(), getFoundationDate(), CadastralStatus.toEnum(getStatus()), type, PersonProfile.toEnum(getProfile()));
			((LegalPerson) person).setBranch(new Branch(getBranchId()));
		}
		
		return person;
	}
}
