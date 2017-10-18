package ru.itis.entity;

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
public class User {
    private Long id;
    private String email;
    private String password;
    private String sex;
    private String country;
    private Boolean newsSubscription;
}
