package ru.itis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 26.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Data
@Builder
@AllArgsConstructor
public class User {
    private Long id;
    private String login;
    private String password;
    private String name;
    private Float weight;
    private Short height;
    private Specialization specialization;
    private Integer xp;
    private Short strength;
    private Short stamina;
    private Short flexibility;
    private Boolean gender;
}
