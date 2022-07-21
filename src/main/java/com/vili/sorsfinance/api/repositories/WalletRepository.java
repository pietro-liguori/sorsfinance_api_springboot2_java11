package com.vili.sorsfinance.api.repositories;

import com.vili.sorsfinance.api.domain.Wallet;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IRepository;

@EntityRef(Wallet.class)
public interface WalletRepository extends IRepository<Wallet> {

}
