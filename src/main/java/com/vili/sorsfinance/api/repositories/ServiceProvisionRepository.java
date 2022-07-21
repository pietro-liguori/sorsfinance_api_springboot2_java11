package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.ServiceProvision;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IRepository;

@EntityRef(ServiceProvision.class)
public interface ServiceProvisionRepository extends IRepository<ServiceProvision> {

}
