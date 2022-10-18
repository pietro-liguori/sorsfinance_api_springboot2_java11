package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.Product;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(Product.class)
public interface ProductRepository extends IRepository<Product> {

}
