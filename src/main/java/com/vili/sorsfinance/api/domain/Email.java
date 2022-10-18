package com.vili.sorsfinance.api.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vili.sorsfinance.api.repositories.EmailRepository;
import com.vili.sorsfinance.api.services.EmailService;
import com.vili.sorsfinance.framework.annotations.RepositoryRef;
import com.vili.sorsfinance.framework.annotations.ServiceRef;
import com.vili.sorsfinance.framework.request.annotations.FilterSetting;

@Entity
@ServiceRef(value = EmailService.class)
@RepositoryRef(value = EmailRepository.class)
@JsonPropertyOrder({ "id", "email", "preferred", "contact" })
public class Email extends BusinessEntity {

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean preferred;
	
	@ManyToOne
	@FilterSetting(alias = "owner", nesting = { "id" })
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "addresses", "phones", "emails" })
	private Contact contact;

	public Email() {
		super();
	}

	public Email(Long id) {
		super(id, Email.class);
	}

	public Email(Long id, String name, Boolean preferred) {
		super(id, Email.class);
		this.name = name;
		this.preferred = preferred;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getPreferred() {
		return preferred;
	}

	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
