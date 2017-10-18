package ru.itis.service;

import ru.itis.dto.UserRegistrationDto;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 02.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class UserRegistrationValidator implements Validator<UserRegistrationDto> {

    private static Set<String> sexSet;
    private static Set<String> countrySet;

    public UserRegistrationValidator() {
        sexSet = new HashSet<>(
                Arrays.asList(
                        new String[]{"male", "female"}
                )
        );
        countrySet = new HashSet<>(
                Arrays.asList(
                        new String[]{"CN", "PL", "RU", "TR", "UA", "AE", "UK", "US"}
                )
        );
    }

    @Override
    public List<String> validate(UserRegistrationDto model) {
        List<String> errors = new ArrayList<>();

        if (!isValidEmail(model.getEmail())) {
            errors.add("Please enter correct email!");
            model.setEmail(null);
        }

        if (!isValidPassword(model.getPassword(), model.getPasswordRepeat())) {
            errors.add("Please enter correct password!");
            model.setPassword(null);
            model.setPasswordRepeat(null);
        }

        if (!isValidSex(model.getSex())) {
            errors.add("Please choose sex!");
            model.setSex(null);
        }

        if (!isValidCountry(model.getCountry())) {
            errors.add("Please choose country!");
            model.setCountry(null);
        }

        if (!isValidNewsSubscription(model.getNewsSubscription())) {
            model.setNewsSubscription(null);
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

    private boolean isValidPassword(String password, String passwordRepeat) {
        return baseValidation(password) && baseValidation(passwordRepeat) && password.equals(passwordRepeat);
    }

    private boolean isValidSex(String sex) {
        return !(!baseValidation(sex) || !sexSet.contains(sex));
    }

    private boolean isValidCountry(String country) {
        return baseValidation(country) && countrySet.contains(country);
    }

    private boolean isValidNewsSubscription(String newsSubscription) {
        return newsSubscription == null || newsSubscription.equals("true");
    }
}
