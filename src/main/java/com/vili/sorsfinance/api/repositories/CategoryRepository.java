package com.vili.sorsfinance.api.repositories;

import java.util.List;

import com.vili.sorsfinance.api.domain.Category;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IRepository;

@EntityRef(Category.class)
public interface CategoryRepository extends IRepository<Category> {

	List<Category> findByNameIgnoreCase(String name);
}
