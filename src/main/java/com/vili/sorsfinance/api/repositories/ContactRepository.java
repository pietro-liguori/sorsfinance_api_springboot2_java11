package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.Contact;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IRepository;

@EntityRef(Contact.class)
public interface ContactRepository extends IRepository<Contact> {

}
