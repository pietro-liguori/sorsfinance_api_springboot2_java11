package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.Voucher;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.controllers.SearchController;

@RestController
@EntityRef(Voucher.class)
@RequestMapping(value = "/vouchers")
public class VoucherResource implements SearchController {

}
