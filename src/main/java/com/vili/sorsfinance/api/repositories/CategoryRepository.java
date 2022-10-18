package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.Category;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(Category.class)
public interface CategoryRepository extends IRepository<Category> {

}
