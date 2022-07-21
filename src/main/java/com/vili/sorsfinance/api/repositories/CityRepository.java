package com.vili.sorsfinance.api.repositories;

import java.util.List;

import com.vili.sorsfinance.api.domain.City;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IRepository;

@EntityRef(City.class)
public interface CityRepository extends IRepository<City> {

	List<City> findByNameIgnoreCase(String name);
}
