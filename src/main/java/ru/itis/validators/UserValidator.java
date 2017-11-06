package ru.itis.validators;

import ru.itis.dto.UserRegistrationDto;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 02.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class UserValidator implements Validator<UserRegistrationDto> {

    @Override
    public List<String> validate(UserRegistrationDto model) {
        List<String> errors = new ArrayList<>();

        if (!isValidEmail(model.getLogin())) {
            errors.add("Please enter correct email!");
            model.setLogin(null);
        }
        return errors;
    }

    private boolean baseValidation(String param) {
        if (param == null) {
            return false;
        }
        param = param.trim();
        return !param.isEmpty();
    }

    private boolean isValidEmail(String email) {
        if (!baseValidation(email)) return false;

        email = email.trim();
        String regex = ".+@.+\\.[a-z]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
