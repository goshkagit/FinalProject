package com.finalproject.upwork.validation.validator;

import com.finalproject.upwork.exception.WrongEmailException;
import com.finalproject.upwork.validation.annotations.Email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<Email, String> {
    @Override
    public void initialize(Email constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.equals(" ") || s.length() == 0) {
            throw new NullPointerException();
        } else {
            Matcher m = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$").matcher(s);
            if (!m.lookingAt()) {
                throw new WrongEmailException(s + " is not an email");
            }
            return m.lookingAt();
        }
    }
}
