package ru.itis.services;

import ru.itis.dao.TrainingDao;
import ru.itis.dao.UserDao;
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
    private final TrainingDao trainingDao;
    private final UserDao userDao;

    public UserTrainingServiceImpl(UserTrainingDao userTrainingDao, TrainingDao trainingDao, UserDao userDao) {
        this.userTrainingDao = userTrainingDao;
        this.trainingDao = trainingDao;
        this.userDao = userDao;
    }

    @Override
    public List<UserTraining> getUserTrainings(UserDto user) {
        return userTrainingDao.findByUserId(user.getId());
    }

    @Override
    public void addUserTraining(UserDto userDto, Integer trainingId, Integer complete) {
        Training training = trainingDao.find(trainingId);
        User user = User.builder()
                .id(userDto.getId())
                .build();

        Integer xp = training.getXp() * complete / 100;

        userDao.updateXp(user, xp);
        userTrainingDao.save(
                UserTraining.builder()
                        .date(LocalDate.now())
                        .user(user)
                        .training(training)
                        .build()
        );
    }
}
