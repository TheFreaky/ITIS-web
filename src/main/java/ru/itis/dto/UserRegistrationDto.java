package ru.itis.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationDto {
    private String name;
    private String password;
    private String login;
}
