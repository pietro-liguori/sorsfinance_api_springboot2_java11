package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.Person;
import com.vili.sorsfinance.api.domain.dto.PersonDTO;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.controllers.IController;

@RestController
@EntityRef(Person.class)
@RequestMapping(value = "/people")
public class PersonResource implements IController<PersonDTO> {

}
