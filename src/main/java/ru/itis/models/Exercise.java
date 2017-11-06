package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 26.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Data
@Builder
@AllArgsConstructor
public class Exercise {
    private Integer id;
    private String name;
}