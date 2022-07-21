package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.Person;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IRepository;

@EntityRef(Person.class)
public interface PersonRepository extends IRepository<Person> {

}
