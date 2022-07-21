package com.vili.sorsfinance.api.repositories;

import java.util.List;

import com.vili.sorsfinance.api.domain.Email;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IRepository;

@EntityRef(Email.class)
public interface EmailRepository extends IRepository<Email> {

	List<Email> findByEmailIgnoreCase(String email);
}
