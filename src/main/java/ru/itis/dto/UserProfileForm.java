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
public class UserProfileForm {
    private String name;
    private String gender;
    private String weight;
    private String height;
}
