package com.finalproject.upwork.validation.annotations;


import com.finalproject.upwork.validation.validator.SizeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SizeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Size {
    String message() default "size must be more than 3 ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
