package com.vili.sorsfinance.api.services;

import org.springframework.stereotype.Service;

import com.vili.sorsfinance.api.domain.Category;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.services.IService;

@Service
@EntityRef(Category.class)
public class CategoryService implements IService {

}
