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
    private String login;
    private Float weight;
    private Short height;
    private Specialization specialization;
    private Integer lvl;
    private Long xp;
    private Long xpToLvlUp;
    private Integer progress;
    private Integer strengthLvl;
    private Integer strengthProgress;
    private Integer staminaLvl;
    private Integer staminaProgress;
    private Integer flexibilityLvl;
    private Integer flexibilityProgress;
    private Boolean gender;
    private List<UserTraining> userTrainings;
}
