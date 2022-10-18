package com.vili.sorsfinance.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vili.sorsfinance.api.domain.Address;
import com.vili.sorsfinance.api.domain.dto.AddressDTO;
import com.vili.sorsfinance.framework.IDataTransferObject;
import com.vili.sorsfinance.framework.IEntity;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.services.IService;

@Service
@EntityRef(Address.class)
public class AddressService implements IService {

	@Override
	public IEntity save(IDataTransferObject dto) {
		AddressDTO addressDTO = (AddressDTO) dto;
		Address address = addressDTO.toEntity();
		address = (Address) save(address);
		
		if (address.getPreferred())
			setAsPreferred(address);
		
		return address;
	}

	private void setAsPreferred(Address address) {
		List<Address> list = address.getContact().getAddresses();
		
		for (Address other : list) {
			if (!address.equals(other)) {
				other.setPreferred(false);
				save(other);
			}
		}	
	}
}
