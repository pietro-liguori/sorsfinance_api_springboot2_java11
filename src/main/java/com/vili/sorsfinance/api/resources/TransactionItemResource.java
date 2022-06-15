package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.entities.TransactionItem;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.api.framework.DefaultResource;

@RestController
@RequestMapping(value = "/transactionitems")
public class TransactionItemResource extends DefaultResource<TransactionItem, DTO<TransactionItem>> {

}
