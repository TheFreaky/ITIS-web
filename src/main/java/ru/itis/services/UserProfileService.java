package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.dto.UserProfileDto;
import ru.itis.dto.UserProfileForm;

/**
 * 07.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface UserProfileService {
    UserProfileDto getUserProfile(Long id);

    UserDto editUserProfile(UserProfileForm profile, UserDto userDto);
}
