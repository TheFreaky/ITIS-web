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
    private Short strengthLvl;
    private Short strengthProgress;
    private Short staminaLvl;
    private Short staminaProgress;
    private Short flexibilityLvl;
    private Short flexibilityProgress;
    private Boolean gender;
    private List<UserTraining> userTrainings;
}
