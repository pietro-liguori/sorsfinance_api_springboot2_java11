package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.Account;
import com.vili.sorsfinance.api.domain.dto.AccountDTO;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.controllers.IController;

@RestController
@EntityRef(Account.class)
@RequestMapping(value = "/accounts")
public class AccountResource implements IController<AccountDTO> {

}
