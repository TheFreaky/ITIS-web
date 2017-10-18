package ru.itis.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationDto {
    private Long id;
    private String email;
    private String password;
    private String passwordRepeat;
    private String sex;
    private String country;
    private String newsSubscription;
}