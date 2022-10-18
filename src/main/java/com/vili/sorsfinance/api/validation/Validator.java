package com.vili.sorsfinance.api.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.data.domain.Example;

import com.vili.sorsfinance.api.domain.Email;
import com.vili.sorsfinance.api.domain.enums.PersonType;
import com.vili.sorsfinance.api.validation.utils.BR;
import com.vili.sorsfinance.framework.exceptions.FieldMessage;
import com.vili.sorsfinance.framework.validation.IValidator;

public class Validator implements IValidator {

	private List<FieldMessage> errors = new ArrayList<>();

	public Validator() {
		
	}
	
	public List<FieldMessage> getErrors() {
		return errors;
	}

	public boolean email(String value, boolean required) {
		String pattern  = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"; // RFC 5322
		
		if (!Pattern.compile(pattern).matcher(value).matches()) {
			addError(new FieldMessage("name", "Invalid email"));
			return false;
		} else {
			Email probe = new Email();
			probe.setName(value);
			return unique("name", Example.of(probe), required);
		}
	}

	public boolean socialId(String value, PersonType type, String locale, boolean required) {
		if (value == null) {
			if (required) {
				addError(new FieldMessage("socialId", "Must not be null"));
				return false;
			}

			return true;
		} else {
			switch (locale) {
			case "BR":
				if (type.equals(PersonType.NATURAL_PERSON)) {
					if (!BR.isValidCPF(value)) {
						addError(new FieldMessage("socialId", "Invalid brazilian natural person socialId (CPF)"));
						return false;
					}
				} else {
					if (!BR.isValidCNPJ(value)) {
						addError(new FieldMessage("socialId", "Invalid brazilian legal person socialId (CNPJ)"));
						return false;
					}
				}
				
				break;
			default:
				break;
			}
		}

		return true;
	}
}
