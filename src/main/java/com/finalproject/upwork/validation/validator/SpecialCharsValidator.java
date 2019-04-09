package com.finalproject.upwork.validation.validator;


import com.finalproject.upwork.exception.SpecialCharsException;
import com.finalproject.upwork.validation.annotations.SpecialChars;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialCharsValidator implements
        ConstraintValidator<SpecialChars, String> {
    @Override
    public void initialize(SpecialChars constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if (s == null || s.equals(" ") || s.length() == 0) {
            throw new NullPointerException();
        } else {
            Matcher m = Pattern.compile("(.*)[^A-Za-z0-9_](.*)").matcher(s);
            if (m.lookingAt()) {
                throw new SpecialCharsException(s + " contains unacceptable chars");
            }
            return !m.lookingAt();
        }
    }
}