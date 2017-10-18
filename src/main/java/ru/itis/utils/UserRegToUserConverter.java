package ru.itis.utils;

import ru.itis.dto.UserRegistrationDto;
import ru.itis.entity.User;

/**
 * 11.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface UserRegToUserConverter extends Converter<UserRegistrationDto, User> {
}
