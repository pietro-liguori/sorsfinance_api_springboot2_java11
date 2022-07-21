package com.vili.sorsfinance.api.repositories;

import java.util.List;

import com.vili.sorsfinance.api.domain.Branch;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IRepository;

@EntityRef(Branch.class)
public interface BranchRepository extends IRepository<Branch> {

	List<Branch> findByNameIgnoreCase(String name);
}
