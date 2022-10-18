package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.Card;
import com.vili.sorsfinance.api.domain.dto.CardDTO;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.controllers.IController;

@RestController
@EntityRef(Card.class)
@RequestMapping(value = "/cards")
public class CardResource implements IController<CardDTO> {

}
