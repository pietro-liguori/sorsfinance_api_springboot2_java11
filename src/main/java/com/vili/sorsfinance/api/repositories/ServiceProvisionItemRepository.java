package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.ServiceProvisionItem;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(ServiceProvisionItem.class)
public interface ServiceProvisionItemRepository extends IRepository<ServiceProvisionItem> {

}
