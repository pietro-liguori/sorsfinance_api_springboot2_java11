package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.ProductItem;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(ProductItem.class)
public interface ProductItemRepository extends IRepository<ProductItem> {

}
