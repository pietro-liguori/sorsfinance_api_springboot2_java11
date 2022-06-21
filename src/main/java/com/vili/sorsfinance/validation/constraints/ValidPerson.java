package com.vili.sorsfinance.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

import com.vili.sorsfinance.validation.PersonValidator;

@Documented
@NotNull(message = "Must not be null")
@Constraint(validatedBy = PersonValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPerson {

	String message() default "Social id validation error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    String locale() default "BR";
}
