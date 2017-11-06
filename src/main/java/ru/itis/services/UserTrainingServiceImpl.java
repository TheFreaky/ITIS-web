package ru.itis.services;

import ru.itis.dao.UserTrainingDao;
import ru.itis.dto.UserDto;
import ru.itis.models.Training;
import ru.itis.models.User;
import ru.itis.models.UserTraining;

import java.time.LocalDate;
import java.util.List;

/**
 * 05.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class UserTrainingServiceImpl implements UserTrainingService {
    private final UserTrainingDao userTrainingDao;

    public UserTrainingServiceImpl(UserTrainingDao userTrainingDao) {
        this.userTrainingDao = userTrainingDao;
    }

    public List<UserTraining> getUserTrainings(UserDto user) {
        return userTrainingDao.findByUserId(user.getId());
    }

    public void addUserTraining(UserDto userDto, Integer trainingId) {
        userTrainingDao.save(
                UserTraining.builder()
                        .date(LocalDate.now())
                        .user(User.builder()
                                .id(userDto.getId())
                                .build())
                        .training(Training.builder()
                                .id(trainingId)
                                .build())
                        .build()
        );
    }
}
