package ru.itis.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 12.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
abstract class AbstractValidator<T> implements Validator<T> {
    boolean baseValidation(String param) {
        if (param == null) {
            return false;
        }
        param = param.trim();
        return !param.isEmpty();
    }

    boolean isValidEmail(String email) {
        if (!baseValidation(email)) return false;

        String regex = ".+@.+\\.[a-z]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
