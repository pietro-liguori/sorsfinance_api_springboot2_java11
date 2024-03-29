package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.Branch;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(Branch.class)
public interface BranchRepository extends IRepository<Branch> {

}
