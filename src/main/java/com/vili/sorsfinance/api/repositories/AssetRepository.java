package com.vili.sorsfinance.api.repositories;

import java.util.List;

import com.vili.sorsfinance.api.domain.Asset;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IRepository;

@EntityRef(Asset.class)
public interface AssetRepository extends IRepository<Asset> {

	List<Asset> findByNameIgnoreCase(String name);
}
