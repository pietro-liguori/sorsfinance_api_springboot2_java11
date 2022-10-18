package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.Country;
import com.vili.sorsfinance.api.domain.dto.CountryDTO;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.controllers.IController;

@RestController
@EntityRef(Country.class)
@RequestMapping(value = "/countries")
public class CountryResource implements IController<CountryDTO> {

}
