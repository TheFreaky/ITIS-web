package ru.itis.service;

import ru.itis.entity.User;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 02.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class UserValidator implements Validator<User> {

    @Override
    public List<String> validate(User model) {
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
        String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
