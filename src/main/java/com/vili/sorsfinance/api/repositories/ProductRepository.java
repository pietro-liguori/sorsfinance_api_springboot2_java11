package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.Product;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IRepository;

@EntityRef(Product.class)
public interface ProductRepository extends IRepository<Product> {

}
