package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.TransactionItem;
import com.vili.sorsfinance.framework.DataTransferObject;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IResource;

@RestController
@EntityRef(TransactionItem.class)
@RequestMapping(value = "/transactionitems")
public class TransactionItemResource implements IResource<DataTransferObject> {

}
