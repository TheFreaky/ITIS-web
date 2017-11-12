package ru.itis.validators;

import ru.itis.dto.UserSignInForm;

import java.util.ArrayList;
import java.util.List;

/**
 * 12.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class UserSignInFormValidator extends AbstractValidator<UserSignInForm> {
    @Override
    public List<String> validate(UserSignInForm model) {
        List<String> errors = new ArrayList<>();

        if (!isValidEmail(model.getLogin())) {
            errors.add("Please enter correct email!");
            model.setLogin(null);
        }

        if (!baseValidation(model.getPassword())) {
            errors.add("Please enter correct password!");
        }

        return errors;
    }
}
