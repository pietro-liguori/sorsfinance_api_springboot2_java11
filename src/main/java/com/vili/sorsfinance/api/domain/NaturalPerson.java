package com.vili.sorsfinance.api.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vili.sorsfinance.api.domain.enums.PersonProfile;
import com.vili.sorsfinance.api.domain.enums.PersonType;
import com.vili.sorsfinance.api.repositories.NaturalPersonRepository;
import com.vili.sorsfinance.api.services.NaturalPersonService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;

@Entity
@ServiceRef(value = NaturalPersonService.class)
@RepositoryRef(value = NaturalPersonRepository.class)
public class NaturalPerson extends Person {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date birthDate;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String nickname;

	public NaturalPerson() {
		super();
		super.setType(PersonType.NATURAL_PERSON);
	}
	
	public NaturalPerson(Long id) {
		super(id, NaturalPerson.class);
		super.setType(PersonType.NATURAL_PERSON);
	}

	public NaturalPerson(Long id, String name, String nickname, String socialId, Date birthDate, PersonType type, PersonProfile profile) {
		super(id, name, socialId, PersonType.NATURAL_PERSON, profile, NaturalPerson.class);
		this.nickname = nickname;
		this.birthDate = birthDate;
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
}
