package ru.itis.validators;

import ru.itis.dto.UserProfileForm;

import java.util.ArrayList;
import java.util.List;

/**
 * 12.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class UserProfileFormValidator extends AbstractValidator<UserProfileForm> {
    public static void main(String[] args) {
        System.out.println(Short.parseShort("asd"));

    }

    @Override
    public List<String> validate(UserProfileForm model) {
        List<String> errors = new ArrayList<>();

        if (!baseValidation(model.getName())) {
            errors.add("Please enter correct name!");
            model.setName(null);
        }

        if (!baseValidation(model.getGender())) {
            errors.add("Please enter correct gender!");
            model.setGender(null);
        }

        if (!isValidHeight(model.getHeight())) {
            errors.add("Please enter correct height!");
            model.setGender(null);
        }

        if (!isValidWeight(model.getWeight())) {
            errors.add("Please enter correct weight!");
            model.setGender(null);
        }

        return errors;
    }

    private boolean isValidHeight(String height) {
        if (!baseValidation(height)) {
            return false;
        } else {
            try {
                Short.parseShort(height);
            } catch (NumberFormatException ex) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidWeight(String weight) {
        if (!baseValidation(weight)) {
            return false;
        } else {
            try {
                Float.parseFloat(weight);
            } catch (NumberFormatException ex) {
                return false;
            }
        }
        return true;
    }

}
