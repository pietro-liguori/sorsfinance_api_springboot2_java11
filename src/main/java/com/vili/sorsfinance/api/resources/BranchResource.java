package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.entities.Branch;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.api.framework.DefaultResource;

@RestController
@RequestMapping(value = "/branches")
public class BranchResource extends DefaultResource<Branch, DTO<Branch>> {

}
