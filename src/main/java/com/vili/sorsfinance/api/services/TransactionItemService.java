package com.vili.sorsfinance.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vili.sorsfinance.api.domain.Asset;
import com.vili.sorsfinance.api.domain.TransactionItem;
import com.vili.sorsfinance.api.domain.dto.TransactionItemDTO;
import com.vili.sorsfinance.framework.IDataTransferObject;
import com.vili.sorsfinance.framework.IEntity;
import com.vili.sorsfinance.framework.annotations.EntityRef;
import com.vili.sorsfinance.framework.services.IService;

@Service
@EntityRef(TransactionItem.class)
public class TransactionItemService implements IService {

	@Autowired
	AssetService assetService;
	
	@Override
	public IEntity save(IDataTransferObject dto) {
		TransactionItemDTO itemDTO = (TransactionItemDTO) dto;
		TransactionItem item = itemDTO.toEntity();
		Asset asset = item.getAsset();
		
		if (asset.getId() == null)
			asset = (Asset) assetService.save(asset);
		
		item.setAsset(asset);
		return IService.super.save(item);
	}
	
	@Override
	public IEntity save(IEntity entity) {
		TransactionItem item = (TransactionItem) entity;
		Asset asset = item.getAsset();
		
		if (asset.getId() == null)
			asset = (Asset) assetService.save(asset);
		
		item.setAsset(asset);
		return IService.super.save(item);
	}
}
