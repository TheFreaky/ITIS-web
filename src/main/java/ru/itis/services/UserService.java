package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.dto.UserRegistrationForm;
import ru.itis.dto.UserSettingForm;
import ru.itis.dto.UserSignInForm;

/**
 * 05.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface UserService {

    UserDto register(UserRegistrationForm user);
    UserDto signIn(UserSignInForm user);
    void editUserData(UserSettingForm form, UserDto userDto);
    void delete(UserDto userDto);
}
