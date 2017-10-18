package ru.itis.utils;

import ru.itis.dto.UserRegistrationDto;
import ru.itis.entity.User;

/**
 * 11.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class UserRegToUserConverterImpl implements UserRegToUserConverter {
    @Override
    public User convertToEntity(UserRegistrationDto dto) {
        return User.builder()
                    .email(dto.getEmail().toLowerCase())
                    .password(dto.getPassword())
                    .sex(dto.getSex())
                    .country(dto.getCountry())
                    .newsSubscription(Boolean.parseBoolean(dto.getNewsSubscription()))
                    .build();
    }

    @Override
    public UserRegistrationDto convertToDto(User entity) {
            return UserRegistrationDto.builder()
                    .email(entity.getEmail())
                    .sex(entity.getSex())
                    .country(entity.getCountry())
                    .newsSubscription(entity.getNewsSubscription().toString())
                    .build();
    }
}
