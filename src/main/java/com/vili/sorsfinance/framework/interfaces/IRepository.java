package com.vili.sorsfinance.framework.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IRepository<T extends IEntity> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

}
