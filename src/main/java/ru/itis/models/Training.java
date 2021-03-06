package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * 26.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Data
@Builder
@AllArgsConstructor
public class Training {
    private Integer id;
    private String name;
    private String description;
    private Integer xp;
    private Short minLvl;
    private Complexity complexity;
    private Specialization type;
    private Set<Exercise> exercises;
}
