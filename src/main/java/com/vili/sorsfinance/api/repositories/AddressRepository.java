package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.Address;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(Address.class)
public interface AddressRepository extends IRepository<Address> {

}
