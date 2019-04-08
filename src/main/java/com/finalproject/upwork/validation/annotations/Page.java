package com.finalproject.upwork.validation.annotations;


import com.finalproject.upwork.validation.validator.PageValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PageValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Page {

    String message() default "field is not an url";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
