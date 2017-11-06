package ru.itis.services;

import ru.itis.dao.TrainingDao;
import ru.itis.dao.UserDao;
import ru.itis.dto.TrainingDto;
import ru.itis.dto.UserDto;
import ru.itis.models.Training;
import ru.itis.models.User;
import ru.itis.utils.UserLevelUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 05.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class TrainingServiceImpl implements TrainingService {
    private final UserDao userDao;
    private final TrainingDao trainingDao;

    public TrainingServiceImpl(UserDao userDao, TrainingDao trainingDao) {
        this.userDao = userDao;
        this.trainingDao = trainingDao;
    }

    @Override
    public List<TrainingDto> getTrainings(UserDto user) {
        Integer lvl = UserLevelUtil.getLvl(userDao.find(user.getId()).getXp());
        return trainingDao.findAllByMinLvlLessThan(lvl).stream()
                .map(training -> TrainingDto.builder()
                        .name(training.getName())
                        .complexity(training.getComplexity())
                        .type(training.getType())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Training getTraining(String name, UserDto userDto) {
        Training training = trainingDao.findByName(name);
        if (training == null) {
            return null;
        }

        User user = userDao.find(userDto.getId());
        if (training.getMinLvl() > UserLevelUtil.getLvl(user.getXp())) {
            training = null;
        }
        return training;
    }
}
