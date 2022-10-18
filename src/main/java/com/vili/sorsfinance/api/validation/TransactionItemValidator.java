package com.vili.sorsfinance.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.vili.sorsfinance.api.domain.Asset;
import com.vili.sorsfinance.api.domain.Branch;
import com.vili.sorsfinance.api.domain.Category;
import com.vili.sorsfinance.api.domain.Person;
import com.vili.sorsfinance.api.domain.dto.TransactionItemDTO;
import com.vili.sorsfinance.api.domain.enums.AssetType;
import com.vili.sorsfinance.api.domain.enums.MeasurementType;
import com.vili.sorsfinance.api.domain.enums.MeasurementUnit;
import com.vili.sorsfinance.api.domain.enums.PersonProfile;
import com.vili.sorsfinance.api.domain.enums.PersonType;
import com.vili.sorsfinance.api.domain.enums.TransactionItemType;
import com.vili.sorsfinance.api.validation.constraints.ValidTransactionItem;
import com.vili.sorsfinance.framework.DTOType;
import com.vili.sorsfinance.framework.exceptions.FieldMessage;

public class TransactionItemValidator implements ConstraintValidator<ValidTransactionItem, TransactionItemDTO> {

	@Override
	public void initialize(ValidTransactionItem ann) {
	}

	@Override
	public boolean isValid(TransactionItemDTO dto, ConstraintValidatorContext context) {
		Validator validator = new Validator();

		if (dto.getMethod() == DTOType.INSERT) {
			validator.positive("price", dto.getPrice(), true);
			validator.positive("quantity", dto.getQuantity(), true);
			validator.positiveOrZero("discount", dto.getDiscount(), true);
			validator.notEmpty("deliveryDate", dto.getDeliveryDate(), false);
			boolean validType = validator.enumValue("type", dto.getType(), TransactionItemType.class, true);
			
			if (dto.getAsset() != null) {
				if (dto.getAsset().getId() != null) {
					validator.entityId("asset.id", dto.getAsset().getId(), Asset.class, true);
					
					if (validType) {
						if (TransactionItemType.toEnum(dto.getType()).equals(TransactionItemType.PRODUCT_ITEM)) {
							if(!AssetType.toEnum(dto.getAsset().getType()).equals(AssetType.PRODUCT))
								validator.addError(new FieldMessage("asset.type", "A transaction item of type '" + TransactionItemType.PRODUCT_ITEM + "' can only have assets of type '" + AssetType.PRODUCT + "'"));
						} else {
							if(!AssetType.toEnum(dto.getAsset().getType()).equals(AssetType.SERVICE_PROVISION))
								validator.addError(new FieldMessage("asset.type", "A transaction item of type '" + TransactionItemType.SERVICE_PROVISION_ITEM + "' can only have assets of type '" + AssetType.SERVICE_PROVISION + "'"));
						}
					}
				} else {
					// TODO criar método em AssetValidator
					if (validator.enumValue("asset.type", dto.getType(), AssetType.class, true)) {
						Asset probe = new Asset();
						probe.setName(dto.getAsset().getName());
						validator.unique("asset.name", Example.of(probe, ExampleMatcher.matching().withIgnoreCase()), true);

						if (AssetType.toEnum(dto.getType()).equals(AssetType.PRODUCT))
							validator.notEmpty("asset.brand", dto.getAsset().getBrand(), true);
						else
							validator.notEmpty("asset.description", dto.getAsset().getDescription(), true);
						
						validator.entityIds("asset.categoryIds", dto.getAsset().getCategoryIds(), Category.class, true);
					}
				}
			}
			
			if (validType) {
				if (TransactionItemType.toEnum(dto.getType()).equals(TransactionItemType.PRODUCT_ITEM)) {
					validator.positive("content", dto.getContent(), false);
					validator.enumValue("contentType", dto.getContentType(), MeasurementType.class, false);
					validator.enumValue("contentUnit", dto.getContentUnit(), MeasurementUnit.class, false);
				} else {
					if (dto.getProvider() != null) {
						if (dto.getProvider().getId() != null)
							validator.entityId("provider.id", dto.getProvider().getId() , Person.class, true);
						else {
							// TODO criar método em PersonValidator
							validator.notEmpty("provider.name", dto.getProvider().getName(), true);
							validator.length("provider.name", dto.getProvider().getName(), 3, 120, true);

							if (validator.enumValue("provider.profile", dto.getProvider().getProfile(), PersonProfile.class, true) & validator.enumValue("type", dto.getType(), PersonType.class, true)) {
								boolean isHolder = PersonProfile.toEnum(dto.getProvider().getProfile()).equals(PersonProfile.HOLDER);
								
								if (validator.enumValue("provider.type", dto.getType(), PersonType.class, true)) {
									PersonType type = PersonType.toEnum(dto.getType());
									
									if (validator.socialId(dto.getProvider().getSocialId(), type, "BR", isHolder)) {
										Person probe = new Person();
										probe.setType(type);
										probe.setSocialId(dto.getProvider().getSocialId());
										validator.unique("provider.socialId", Example.of(probe), true);
									}
									
									if (type.equals(PersonType.LEGAL_PERSON)) {
										validator.entityId("provider.branchId", dto.getProvider().getBranchId(), Branch.class, true);
									}
									
									if (isHolder) {
										switch (type) {
										case LEGAL_PERSON:
											validator.notEmpty("provider.foundationDate", dto.getProvider().getFoundationDate(), true);
											break;
										case NATURAL_PERSON:
											validator.notEmpty("provider.birthDate", dto.getProvider().getBirthDate(), true);
											break;
										}
									}
								}
							}
						}
					}
				}
			}

		} else {

		}

		return validator.validate(context);
	}
}
