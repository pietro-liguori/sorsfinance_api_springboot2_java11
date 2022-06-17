package com.vili.sorsfinance.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.vili.sorsfinance.api.entities.enums.PersonProfile;
import com.vili.sorsfinance.validation.PersonIdValidator;

@Documented
@Constraint(validatedBy = PersonIdValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPersonId {

	String message() default "Person id validation error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    PersonProfile profile() default PersonProfile.STANDARD;
    
    boolean acceptAll() default false;
}
