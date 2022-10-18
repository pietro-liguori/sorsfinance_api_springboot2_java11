package com.vili.sorsfinance.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vili.sorsfinance.api.domain.Email;
import com.vili.sorsfinance.api.domain.dto.EmailDTO;
import com.vili.sorsfinance.framework.IDataTransferObject;
import com.vili.sorsfinance.framework.IEntity;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.services.IService;

@Service
@EntityRef(Email.class)
public class EmailService implements IService {

	@Override
	public IEntity save(IDataTransferObject dto) {
		EmailDTO emailDTO = (EmailDTO) dto;
		Email email = emailDTO.toEntity();
		email = (Email) save(email);
		
		if (email.getPreferred())
			setAsPreferred(email);
		
		return email;
	}

	private void setAsPreferred(Email email) {
		List<Email> list = email.getContact().getEmails();
		
		for (Email other : list) {
			if (!email.equals(other)) {
				other.setPreferred(false);
				save(other);
			}
		}	
	}
}
