package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.CreditCardStatement;
import com.vili.sorsfinance.framework.DataTransferObject;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.controllers.IController;

@RestController
@EntityRef(CreditCardStatement.class)
@RequestMapping(value = "/creditcardstatements")
public class CreditCardStatementResource implements IController<DataTransferObject> {

}
