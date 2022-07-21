package com.vili.sorsfinance.api.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vili.sorsfinance.api.domain.Asset;
import com.vili.sorsfinance.api.domain.dto.TransactionItemDTO;
import com.vili.sorsfinance.api.repositories.AssetRepository;
import com.vili.sorsfinance.api.repositories.TransactionItemRepository;
import com.vili.sorsfinance.api.validation.constraints.ValidTransactionItem;
import com.vili.sorsfinance.framework.FieldMessage;
import com.vili.sorsfinance.framework.enums.DTOType;

public class TransactionItemValidator implements ConstraintValidator<ValidTransactionItem, TransactionItemDTO> {

	@Autowired
	TransactionItemRepository itemRepo;
	@Autowired
	AssetRepository assetRepo;
	
	@Override
	public void initialize(ValidTransactionItem ann) {
	}

	@Override
	public boolean isValid(TransactionItemDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (dto.getMethod() == DTOType.INSERT) {
			if (dto.getAssetId() == null)
				list.add(new FieldMessage("assetId", "Must not be null"));
			else {
				Optional<Asset> aux = assetRepo.findById(dto.getAssetId());

				if (aux.isEmpty())
					list.add(new FieldMessage("assetId", "Asset not found: " + dto.getAssetId()));
			}
			
			if (dto.getPrice() == null)
				list.add(new FieldMessage("price", "Must not be null"));
			else
				if (dto.getPrice() <= 0)
					list.add(new FieldMessage("price", "Must be positive"));

			if (dto.getQuantity() == null)
				list.add(new FieldMessage("quantity", "Must not be null"));
			else
				if (dto.getQuantity() < 1)
					list.add(new FieldMessage("quantity", "Must be positive"));

			if (dto.getDiscount() == null)
				list.add(new FieldMessage("discount", "Must not be null"));
			else
				if (dto.getDiscount() < 0)
					list.add(new FieldMessage("discount", "Must be positive or zero"));

		} else if (dto.getMethod() == DTOType.UPDATE) {
			// TODO all item types update validation
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
					.addConstraintViolation();
		}

		return list.isEmpty();
	}
}
