package com.vili.sorsfinance.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.vili.sorsfinance.api.domain.Asset;
import com.vili.sorsfinance.api.domain.Category;
import com.vili.sorsfinance.api.domain.dto.AssetDTO;
import com.vili.sorsfinance.api.domain.enums.AssetType;
import com.vili.sorsfinance.api.validation.constraints.ValidAsset;
import com.vili.sorsfinance.framework.DTOType;

public class AssetValidator implements ConstraintValidator<ValidAsset, AssetDTO> {

	@Override
	public void initialize(ValidAsset ann) {
	}

	@Override
	public boolean isValid(AssetDTO dto, ConstraintValidatorContext context) {
		Validator validator = new Validator();
		
		if (dto.getMethod().equals(DTOType.INSERT)) {
			if (validator.enumValue("type", dto.getType(), AssetType.class, true)) {
				Asset probe = new Asset();
				probe.setName(dto.getName());
				validator.unique("name", Example.of(probe, ExampleMatcher.matching().withIgnoreCase()), true);

				if (AssetType.toEnum(dto.getType()).equals(AssetType.PRODUCT))
					validator.notEmpty("brand", dto.getBrand(), true);
				else
					validator.notEmpty("description", dto.getDescription(), true);
				
				validator.entityIds("categoryIds", dto.getCategoryIds(), Category.class, true);
			}
		} else {
			
		}

		return validator.validate(context);
	}
}
