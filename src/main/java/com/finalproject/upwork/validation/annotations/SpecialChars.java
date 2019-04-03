package com.finalproject.upwork.validation.annotations;

import com.finalproject.upwork.validation.validator.SpecialCharsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SpecialCharsValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SpecialChars {

    String message() default "Unacceptable chars";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
