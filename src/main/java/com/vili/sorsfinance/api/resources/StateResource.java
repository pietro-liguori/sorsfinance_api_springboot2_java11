package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.State;
import com.vili.sorsfinance.api.domain.dto.StateDTO;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.interfaces.IResource;

@RestController
@EntityRef(State.class)
@RequestMapping(value = "/states")
public class StateResource implements IResource<StateDTO> {

}
