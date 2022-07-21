package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.Email;
import com.vili.sorsfinance.api.domain.dto.EmailDTO;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IResource;

@RestController
@EntityRef(Email.class)
@RequestMapping(value = "/emails")
public class EmailResource implements IResource<EmailDTO> {

}
