package ru.itis.services;

import ru.itis.dto.UserProfileDto;

/**
 * 07.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface UserProfileService {
    UserProfileDto getUserProfile(Long id);

    UserProfileDto editUserProfile(UserProfileDto profile);
}
