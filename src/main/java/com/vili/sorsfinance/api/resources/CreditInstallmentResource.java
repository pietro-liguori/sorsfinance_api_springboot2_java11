package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.CreditInstallment;
import com.vili.sorsfinance.framework.DataTransferObject;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.controllers.IController;

@RestController
@EntityRef(CreditInstallment.class)
@RequestMapping(value = "/creditinstallments")
public class CreditInstallmentResource implements IController<DataTransferObject> {

}
