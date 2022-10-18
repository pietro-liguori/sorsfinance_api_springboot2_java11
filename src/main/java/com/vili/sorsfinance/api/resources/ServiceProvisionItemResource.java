package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.ServiceProvisionItem;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.controllers.SearchController;

@RestController
@EntityRef(ServiceProvisionItem.class)
@RequestMapping(value = "/serviceProvisionItems")
public class ServiceProvisionItemResource implements SearchController {

}
