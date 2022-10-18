package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.ProductItem;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.controllers.SearchController;

@RestController
@EntityRef(ProductItem.class)
@RequestMapping(value = "/productItems")
public class ProductItemResource implements SearchController {

}
