package ru.itis.services;

import ru.itis.dto.TrainingDto;
import ru.itis.dto.UserDto;
import ru.itis.models.Training;

import java.util.List;

/**
 * 05.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface TrainingService {

    List<TrainingDto> getTrainings(UserDto user);
    List<TrainingDto> getTrainingsSortedByType(UserDto user);
    List<TrainingDto> getTrainingsSortedByComplexity(UserDto user);
    Training getTraining(String name, UserDto user);
    void addUserTraining(UserDto userDto, String trainingName, Integer doneEx);
}
