package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.Contact;
import com.vili.sorsfinance.api.domain.dto.ContactDTO;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IResource;

@RestController
@EntityRef(Contact.class)
@RequestMapping(value = "/contacts")
public class ContactResource implements IResource<ContactDTO> {

}
