package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.Bankslip;
import com.vili.sorsfinance.framework.DataTransferObject;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.controllers.IController;

@RestController
@EntityRef(Bankslip.class)
@RequestMapping(value = "/bankslips")
public class BankslipResource implements IController<DataTransferObject> {

}
