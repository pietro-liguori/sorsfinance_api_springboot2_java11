package com.vili.sorsfinance.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vili.sorsfinance.api.domain.NaturalPerson;
import com.vili.sorsfinance.api.domain.Person;
import com.vili.sorsfinance.api.domain.Wallet;
import com.vili.sorsfinance.api.domain.dto.PersonDTO;
import com.vili.sorsfinance.api.domain.enums.AccountStatus;
import com.vili.sorsfinance.api.domain.enums.AccountType;
import com.vili.sorsfinance.api.domain.enums.PersonProfile;
import com.vili.sorsfinance.framework.DTOType;
import com.vili.sorsfinance.framework.IDataTransferObject;
import com.vili.sorsfinance.framework.IEntity;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.services.IService;

@Service
@EntityRef(NaturalPerson.class)
public class NaturalPersonService implements IService {

	@Autowired
	WalletService walletService;
	
	@Override
	public IEntity save(IDataTransferObject dto) {
		PersonDTO personDTO = (PersonDTO) dto;
		Person person = personDTO.toEntity();
		person = (Person) save(person);
		
		if (dto.getMethod().equals(DTOType.INSERT)) {
			if (person.getProfile().equals(PersonProfile.HOLDER.getCode())) {
				String walletName = "Carteira " + person.getName();
				Wallet wallet = new Wallet(null, walletName, person, 0.0, 0.0, AccountType.WALLET, AccountStatus.ACTIVE);
				wallet = (Wallet) walletService.save(wallet);
			}
		}
		
		return person;
	}
}
