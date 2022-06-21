package com.vili.sorsfinance.api.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vili.sorsfinance.api.entities.Asset;
import com.vili.sorsfinance.api.entities.Product;
import com.vili.sorsfinance.api.entities.ServiceProvision;
import com.vili.sorsfinance.api.entities.dto.AssetDTO;
import com.vili.sorsfinance.api.entities.enums.AssetType;
import com.vili.sorsfinance.api.entities.filters.AssetFilter;
import com.vili.sorsfinance.api.framework.DefaultResource;
import com.vili.sorsfinance.api.framework.EntityFilter;
import com.vili.sorsfinance.api.framework.Request;
import com.vili.sorsfinance.api.repositories.AssetRepository;

@RestController
@RequestMapping(value = "/assets")
public class AssetResource extends DefaultResource<Asset, AssetDTO> {

	@Autowired AssetRepository repo;

	@Override
	@SuppressWarnings("unchecked")
	protected <U extends EntityFilter<Asset>> U getFilter() {
		Request req = Request.from(getRequest());
		return (U) new AssetFilter(req, repo);
	}
	
	@Override
	@PostMapping
	public ResponseEntity<Asset> insert(@Valid @RequestBody AssetDTO dto) {
		AssetType type = AssetType.toEnum(dto.getType());
		Asset obj = null;

		if (AssetType.PRODUCT.equals(type))
			obj = service.save(Product.fromDTO(dto));
		
		if (AssetType.SERVICE_PROVISION.equals(type))
			obj = service.save(ServiceProvision.fromDTO(dto));

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
}
