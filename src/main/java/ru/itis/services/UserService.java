package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.dto.UserRegistrationDto;
import ru.itis.dto.UserSignInDto;

/**
 * 05.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface UserService {
    UserDto register(UserRegistrationDto user);

    UserDto signIn(UserSignInDto user);
}
