package com.finalproject.upwork.validation.validator;

import com.finalproject.upwork.exception.NotUrlException;
import com.finalproject.upwork.exception.SizeException;
import com.finalproject.upwork.validation.annotations.Size;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SizeValidator implements
        ConstraintValidator<Size, String> {
    @Override
    public void initialize(Size constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.equals(" ") || s.length() == 0) {
            throw new NullPointerException();
        } else {
            if (s.length() < 3 || s.length() > 500) {
                throw new SizeException(s + "  size less than 3 or more than 500");
            }
            return !(s.length() < 3);
        }
    }
}
