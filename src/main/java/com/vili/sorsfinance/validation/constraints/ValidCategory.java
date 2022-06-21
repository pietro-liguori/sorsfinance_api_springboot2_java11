package com.vili.sorsfinance.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;

import com.vili.sorsfinance.validation.CategoryValidator;

@Documented
@NotBlank(message = "Must not be null or empty")
@Constraint(validatedBy = CategoryValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCategory {

	String message() default "Category validation error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
