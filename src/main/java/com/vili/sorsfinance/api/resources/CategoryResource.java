package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.Category;
import com.vili.sorsfinance.api.domain.dto.CategoryDTO;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IResource;

@RestController
@EntityRef(Category.class)
@RequestMapping(value = "/categories")
public class CategoryResource implements IResource<CategoryDTO> {

}
