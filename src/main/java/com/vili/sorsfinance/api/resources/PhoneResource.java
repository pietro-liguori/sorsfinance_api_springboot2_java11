package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.Phone;
import com.vili.sorsfinance.api.domain.dto.PhoneDTO;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.controllers.IController;

@RestController
@EntityRef(Phone.class)
@RequestMapping(value = "/phones")
public class PhoneResource implements IController<PhoneDTO>{

}
