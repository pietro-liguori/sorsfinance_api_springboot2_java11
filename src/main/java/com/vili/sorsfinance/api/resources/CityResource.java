package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.City;
import com.vili.sorsfinance.api.domain.dto.CityDTO;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.controllers.IController;

@RestController
@EntityRef(City.class)
@RequestMapping(value = "/cities")
public class CityResource implements IController<CityDTO> {

}
