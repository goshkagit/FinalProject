package com.finalproject.upwork.validation.validator;

import com.finalproject.upwork.exception.NotUrlException;
import com.finalproject.upwork.validation.annotations.Page;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageValidator implements ConstraintValidator<Page, String> {
    @Override
    public void initialize(Page constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.equals(" ") || s.length() == 0) {
            throw new NullPointerException();
        } else {
            Matcher m = Pattern.compile("\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]").matcher(s);
            if (!m.lookingAt()) {
                throw new NotUrlException(s + " is not an url");
            }
            return m.lookingAt();
        }

    }
}
