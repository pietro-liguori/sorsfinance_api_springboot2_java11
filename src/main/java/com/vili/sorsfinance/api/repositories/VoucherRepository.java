package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.Voucher;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.repositories.IRepository;

@EntityRef(Voucher.class)
public interface VoucherRepository extends IRepository<Voucher> {

}
