package com.vili.sorsfinance.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.vili.sorsfinance.validation.UniqueAssetValidator;

@Documented
@NotBlank(message = "Must not be null or empty")
@Length(min = 2, max = 80, message = "Must be between 2 and 80 characters")
@Constraint(validatedBy = UniqueAssetValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueAsset {

	String message() default "Asset validation error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
