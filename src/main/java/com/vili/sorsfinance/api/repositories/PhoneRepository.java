package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.Phone;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IRepository;

@EntityRef(Phone.class)
public interface PhoneRepository extends IRepository<Phone> {

}
