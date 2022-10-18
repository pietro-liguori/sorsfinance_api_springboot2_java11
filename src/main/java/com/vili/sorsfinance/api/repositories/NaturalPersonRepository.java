package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.NaturalPerson;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(NaturalPerson.class)
public interface NaturalPersonRepository extends IRepository<NaturalPerson> {

}
