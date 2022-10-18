package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.LegalPerson;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(LegalPerson.class)
public interface LegalPersonRepository extends IRepository<LegalPerson> {

}
