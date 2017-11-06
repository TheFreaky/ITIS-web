package ru.itis.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.models.Specialization;
import ru.itis.models.UserTraining;

import java.util.List;

@Data
@Builder
public class UserProfileDto {
    private Long id;
    private String name;
    private Float weight;
    private Short height;
    private Specialization specialization;
    private Integer lvl;
    private Short strength;
    private Short stamina;
    private Short flexibility;
    private Boolean gender;
    private List<UserTraining> userTrainings;
}
