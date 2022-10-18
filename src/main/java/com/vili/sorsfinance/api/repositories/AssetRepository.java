package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.Asset;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(Asset.class)
public interface AssetRepository extends IRepository<Asset> {

}
