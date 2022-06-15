package com.vili.sorsfinance.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vili.sorsfinance.api.entities.Asset;
import com.vili.sorsfinance.api.entities.filters.AssetFilter;
import com.vili.sorsfinance.api.framework.DTO;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.AssetRepository;

@RestController
@RequestMapping(value = "/assets")
public class AssetResource extends DefaultResource<Asset, DTO<Asset>> {

	@Autowired AssetRepository repo;

	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<Asset>> U getFilter() {
		Request req = Request.from(getRequest());
		return (U) new AssetFilter(req, repo);
	}
}
