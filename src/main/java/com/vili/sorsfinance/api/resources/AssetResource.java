package com.vili.sorsfinance.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.domain.Asset;
import com.vili.sorsfinance.api.domain.dto.AssetDTO;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.controllers.IController;

@RestController
@EntityRef(Asset.class)
@RequestMapping(value = "/assets")
public class AssetResource implements IController<AssetDTO> {

}
