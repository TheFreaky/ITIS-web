package ru.itis.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.models.Complexity;
import ru.itis.models.Specialization;

/**
 * 07.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
@Data
@Builder
public class TrainingDto {
    private String name;
    private Complexity complexity;
    private Specialization type;
}
