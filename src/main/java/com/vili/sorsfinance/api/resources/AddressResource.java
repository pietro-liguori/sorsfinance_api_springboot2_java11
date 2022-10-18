package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.Address;
import com.vili.sorsfinance.api.domain.dto.AddressDTO;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.controllers.IController;

@RestController
@EntityRef(Address.class)
@RequestMapping(value = "/addresses")
public class AddressResource implements IController<AddressDTO> {

}
