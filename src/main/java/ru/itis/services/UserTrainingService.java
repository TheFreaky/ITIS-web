package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.models.UserTraining;

import java.util.List;

/**
 * 05.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface UserTrainingService {
    List<UserTraining> getUserTrainings(UserDto user);

    void addUserTraining(UserDto userDto, Integer trainingId, Integer complete);
}
