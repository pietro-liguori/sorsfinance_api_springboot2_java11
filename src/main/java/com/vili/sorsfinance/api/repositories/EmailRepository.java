package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.Email;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(Email.class)
public interface EmailRepository extends IRepository<Email> {

}
