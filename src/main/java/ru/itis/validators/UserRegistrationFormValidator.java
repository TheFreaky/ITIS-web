package ru.itis.validators;

import ru.itis.dto.UserRegistrationForm;

import java.util.ArrayList;
import java.util.List;

/**
 * 02.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class UserRegistrationFormValidator extends AbstractValidator<UserRegistrationForm> {

    @Override
    public List<String> validate(UserRegistrationForm model) {
        List<String> errors = new ArrayList<>();

        if (!isValidEmail(model.getLogin())) {
            errors.add("Please enter correct email!");
            model.setLogin(null);
        }

        if (!baseValidation(model.getName())) {
            errors.add("Please enter correct name!");
            model.setName(null);
        }

        if (!baseValidation(model.getPassword())) {
            errors.add("Please enter correct password!");
        }

        return errors;
    }
}
