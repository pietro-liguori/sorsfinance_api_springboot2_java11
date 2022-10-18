package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.BankAccount;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.controllers.SearchController;

@RestController
@EntityRef(BankAccount.class)
@RequestMapping(value = "/bankAccounts")
public class BankAccountResource implements SearchController {

}
