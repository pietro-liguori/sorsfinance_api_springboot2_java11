package com.vili.sorsfinance.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vili.sorsfinance.api.domain.Phone;
import com.vili.sorsfinance.api.domain.dto.PhoneDTO;
import com.vili.sorsfinance.framework.IDataTransferObject;
import com.vili.sorsfinance.framework.IEntity;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.services.IService;

@Service
@EntityRef(Phone.class)
public class PhoneService implements IService {

	@Override
	public IEntity save(IDataTransferObject dto) {
		PhoneDTO phoneDTO = (PhoneDTO) dto;
		Phone phone = phoneDTO.toEntity();
		phone = (Phone) save(phone);
		
		if (phone.getPreferred())
			setAsPreferred(phone);
		
		return phone;
	}

	private void setAsPreferred(Phone phone) {
		List<Phone> list = phone.getContact().getPhones();
		
		for (Phone other : list) {
			if (!phone.equals(other)) {
				other.setPreferred(false);
				save(other);
			}
		}	
	}
}
