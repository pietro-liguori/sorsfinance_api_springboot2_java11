package com.vili.sorsfinance.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

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
import com.vili.sorsfinance.api.repositories.CityRepository;
import com.vili.sorsfinance.validation.constraints.ValidEnumValue;

public class EnumValidator implements ConstraintValidator<ValidEnumValue, Integer> {

	@Autowired
	CityRepository repo;

	private Class<?> target;
	
	@Override
	public void initialize(ValidEnumValue ann) {
		setTarget(ann.target());
	}

	@Override
	public boolean isValid(Integer code, ConstraintValidatorContext context) {
		List<String> list = new ArrayList<>();

		if (code != null) {
			try {
				if (target.getSimpleName().equals(AccountStatus.class.getSimpleName()))
					AccountStatus.toEnum(code);
				else if (target.getSimpleName().equals(AccountType.class.getSimpleName()))
					AccountType.toEnum(code);
				else if (target.getSimpleName().equals(AssetType.class.getSimpleName()))
					AssetType.toEnum(code);
				else if (target.getSimpleName().equals(CardStatus.class.getSimpleName()))
					CardStatus.toEnum(code);
				else if (target.getSimpleName().equals(CardType.class.getSimpleName()))
					CardType.toEnum(code);
				else if (target.getSimpleName().equals(ContactType.class.getSimpleName()))
					ContactType.toEnum(code);
				else if (target.getSimpleName().equals(InstallmentOption.class.getSimpleName()))
					InstallmentOption.toEnum(code);
				else if (target.getSimpleName().equals(PaymentStatus.class.getSimpleName()))
					PaymentStatus.toEnum(code);
				else if (target.getSimpleName().equals(PaymentType.class.getSimpleName()))
					PaymentType.toEnum(code);
				else if (target.getSimpleName().equals(PeriodUnit.class.getSimpleName()))
					PeriodUnit.toEnum(code);
				else if (target.getSimpleName().equals(PersonProfile.class.getSimpleName()))
					PersonProfile.toEnum(code);
				else if (target.getSimpleName().equals(PersonType.class.getSimpleName()))
					PersonType.toEnum(code);
				else if (target.getSimpleName().equals(PhoneType.class.getSimpleName()))
					PhoneType.toEnum(code);
				else if (target.getSimpleName().equals(TransactionType.class.getSimpleName()))
					TransactionType.toEnum(code);
				else
					list.add("Validation not implemented for '" + target.getSimpleName() + "'");
			} catch (Exception e) {
				list.add(e.getMessage());
			}
		}

		for (String msg : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
		}

		return list.isEmpty();
	}

	public Class<?> getTarget() {
		return target;
	}

	public void setTarget(Class<?> target) {
		this.target = target;
	}
}
