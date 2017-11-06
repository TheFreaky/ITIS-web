package ru.itis.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 25.09.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Data
@Builder
public class UserDto {
    private Long id;
    private String name;
}
