package com.vili.sorsfinance.api.services;

import org.springframework.stereotype.Service;

import com.vili.sorsfinance.api.domain.ServiceProvision;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IService;

@Service
@EntityRef(ServiceProvision.class)
public class ServiceProvisionService implements IService {

}
