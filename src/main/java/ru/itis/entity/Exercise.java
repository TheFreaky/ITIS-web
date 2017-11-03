package ru.itis.entity;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * 26.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    private Integer id;
    private String name;
    private Complexity complexity;
    private Specialization type;
    private String description;
    private String instruction;
    private String fails;
    private String resource;
}