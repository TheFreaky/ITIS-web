package ru.itis.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationForm {
    private String name;
    private String password;
    private String login;
}
