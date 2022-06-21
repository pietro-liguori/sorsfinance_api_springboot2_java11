package com.vili.sorsfinance.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vili.sorsfinance.api.entities.enums.AccountStatus;
import com.vili.sorsfinance.api.entities.enums.AccountType;
import com.vili.sorsfinance.api.entities.enums.AssetType;
import com.vili.sorsfinance.api.entities.enums.CardStatus;
import com.vili.sorsfinance.api.entities.enums.CardType;
import com.vili.sorsfinance.api.entities.enums.ContactType;
import com.vili.sorsfinance.api.entities.enums.InstallmentOption;
import com.vili.sorsfinance.api.entities.enums.PaymentStatus;
import com.vili.sorsfinance.api.entities.enums.PaymentType;
import com.vili.sorsfinance.api.entities.enums.PeriodUnit;
import com.vili.sorsfinance.api.entities.enums.PersonProfile;
import com.vili.sorsfinance.api.entities.enums.PersonType;
import com.vili.sorsfinance.api.entities.enums.PhoneType;
import com.vili.sorsfinance.api.entities.enums.TransactionType;
import com.vili.sorsfinance.api.framework.FieldMessage;
import com.vili.sorsfinance.validation.constraints.ValidEnumValue;

public class EnumValidator implements ConstraintValidator<ValidEnumValue, Integer> {

	private Class<?> target;
	
	@Override
	public void initialize(ValidEnumValue ann) {
		setTarget(ann.target());
	}

	@Override
	public boolean isValid(Integer code, ConstraintValidatorContext context) {
		List<FieldMessage> list = validate(code, target);

		for (FieldMessage x : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(x.getMessage()).addConstraintViolation();
		}

		return list.isEmpty();
	}

	public Class<?> getTarget() {
		return target;
	}

	public void setTarget(Class<?> target) {
		this.target = target;
	}
	
	public static List<FieldMessage> validate(Integer code, Class<?> target) {
		List<FieldMessage> list = new ArrayList<>();
		String fieldName = "enumField";
		
		if (code != null) {
			try {
				if (target.getSimpleName().equals(AccountStatus.class.getSimpleName())) {
					fieldName = "status";
					AccountStatus.toEnum(code);
				}
				else if (target.getSimpleName().equals(AccountType.class.getSimpleName())) {
					fieldName = "type";
					AccountType.toEnum(code);
				}
				else if (target.getSimpleName().equals(AssetType.class.getSimpleName())) {
					fieldName = "type";
					AssetType.toEnum(code);
				}
				else if (target.getSimpleName().equals(CardStatus.class.getSimpleName())) {
					fieldName = "status";
					CardStatus.toEnum(code);
				}
				else if (target.getSimpleName().equals(CardType.class.getSimpleName())) {
					fieldName = "type";
					CardType.toEnum(code);
				}
				else if (target.getSimpleName().equals(ContactType.class.getSimpleName())) {
					fieldName = "type";
					ContactType.toEnum(code);
				}
				else if (target.getSimpleName().equals(InstallmentOption.class.getSimpleName())) {
					fieldName = "option";
					InstallmentOption.toEnum(code);
				}
				else if (target.getSimpleName().equals(PaymentStatus.class.getSimpleName())) {
					fieldName = "status";
					PaymentStatus.toEnum(code);
				}
				else if (target.getSimpleName().equals(PaymentType.class.getSimpleName())) {
					fieldName = "type";
					PaymentType.toEnum(code);
				}
				else if (target.getSimpleName().equals(PeriodUnit.class.getSimpleName())) {
					fieldName = "unit";
					PeriodUnit.toEnum(code);
				}
				else if (target.getSimpleName().equals(PersonProfile.class.getSimpleName())) {
					fieldName = "profile";
					PersonProfile.toEnum(code);
				}
				else if (target.getSimpleName().equals(PersonType.class.getSimpleName())) {
					fieldName = "type";
					PersonType.toEnum(code);
				}
				else if (target.getSimpleName().equals(PhoneType.class.getSimpleName())) {
					fieldName = "type";
					PhoneType.toEnum(code);
				}
				else if (target.getSimpleName().equals(TransactionType.class.getSimpleName())) {
					fieldName = "type";
					TransactionType.toEnum(code);
				}
				else
					list.add(new FieldMessage(fieldName, "Validation not implemented for '" + target.getSimpleName() + "'"));
			} catch (Exception e) {
				list.add(new FieldMessage(fieldName, e.getMessage()));
			}
		} else {
			list.add(new FieldMessage(fieldName, "Must not be null"));
		}

		return list;
	}
}
