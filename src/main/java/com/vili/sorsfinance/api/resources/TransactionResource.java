package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.Transaction;
import com.vili.sorsfinance.api.domain.dto.TransactionDTO;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.controllers.IController;

@RestController
@EntityRef(Transaction.class)
@RequestMapping(value = "/transactions")
public class TransactionResource implements IController<TransactionDTO>{

}
