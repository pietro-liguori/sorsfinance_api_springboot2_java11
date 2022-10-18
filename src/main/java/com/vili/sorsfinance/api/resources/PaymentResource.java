package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.Payment;
import com.vili.sorsfinance.api.domain.dto.PaymentDTO;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.controllers.IController;

@RestController
@EntityRef(Payment.class)
@RequestMapping(value = "/payments")
public class PaymentResource implements IController<PaymentDTO> {

}
