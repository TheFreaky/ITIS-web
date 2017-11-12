package ru.itis.validators;

import ru.itis.dto.UserSettingForm;

import java.util.ArrayList;
import java.util.List;

/**
 * 12.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class UserSettingFormValidator extends AbstractValidator<UserSettingForm> {
    @Override
    public List<String> validate(UserSettingForm model) {
        List<String> errors = new ArrayList<>();

        if (!isValidEmail(model.getLogin())) {
            errors.add("Please enter correct email!");
            model.setLogin(null);
        }

        if (!baseValidation(model.getPassword()) ||
                !baseValidation(model.getPasswordRepeat()) ||
                !model.getPassword().equals(model.getPasswordRepeat())) {
            errors.add("Please enter correct password!");
        }

        return errors;
    }
}
