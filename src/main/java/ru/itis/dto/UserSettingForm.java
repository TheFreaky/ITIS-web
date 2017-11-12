package ru.itis.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 12.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Data
@Builder
public class UserSettingForm {
    private String login;
    private String password;
    private String passwordRepeat;
}
