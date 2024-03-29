package com.vili.sorsfinance.api.services;

import org.springframework.stereotype.Service;

import com.vili.sorsfinance.api.domain.State;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.services.IService;

@Service
@EntityRef(State.class)
public class StateService implements IService {

}
