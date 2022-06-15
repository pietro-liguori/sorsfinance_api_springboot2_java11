package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.entities.Category;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.api.framework.DefaultResource;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource extends DefaultResource<Category, DTO<Category>> {

}
