package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.Branch;
import com.vili.sorsfinance.api.domain.dto.BranchDTO;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IResource;

@RestController
@EntityRef(Branch.class)
@RequestMapping(value = "/branches")
public class BranchResource implements IResource<BranchDTO> {

}
