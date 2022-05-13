package com.pirko.mandaty.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class NameValidator implements ConstraintValidator<IsName, String> {

    private static final Pattern namePattern = Pattern.compile("[A-Z][a-z]{1,19}");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return namePattern.matcher(s).matches();
    }
}
