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
@AllArgsConstructor
public class Equipment {
    private Integer id;
    private String name;
}
