package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.Wallet;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.controllers.SearchController;

@RestController
@EntityRef(Wallet.class)
@RequestMapping(value = "/wallets")
public class WalletResource implements SearchController {

}
