package ru.itis.services.impl;

import ru.itis.dao.TrainingDao;
import ru.itis.dao.UserDao;
import ru.itis.dao.UserTrainingDao;
import ru.itis.dto.TrainingDto;
import ru.itis.dto.UserDto;
import ru.itis.models.Training;
import ru.itis.models.User;
import ru.itis.models.UserTraining;
import ru.itis.services.TrainingService;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
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
    private final UserTrainingDao userTrainingDao;

    public TrainingServiceImpl(UserDao userDao, TrainingDao trainingDao, UserTrainingDao userTrainingDao) {
        this.userDao = userDao;
        this.trainingDao = trainingDao;
        this.userTrainingDao = userTrainingDao;
    }

    @Override
    public List<TrainingDto> getTrainings(UserDto user) {
        return trainingToTrainingDto(user, trainingDao::findAllByMinLvlLessThan);
    }

    @Override
    public List<TrainingDto> getTrainingsSortedByType(UserDto user) {
        return trainingToTrainingDto(user, trainingDao::findAllByMinLvlLessThanOrderByType);
    }

    @Override
    public List<TrainingDto> getTrainingsSortedByComplexity(UserDto user) {
        return trainingToTrainingDto(user, trainingDao::findAllByMinLvlLessThanOrderByComplexity);
    }

    @Override
    public Training getTraining(String name, UserDto userDto) {
        Training training = trainingDao.findByName(name);
        if (training == null) {
            return null;
        }

        User user = userDao.findById(userDto.getId());
        if (training.getMinLvl() > LevelService.getLvl(user.getXp())) {
            training = null;
        }
        return training;
    }

    @Override
    public void addUserTraining(UserDto userDto, String trainingName, Integer doneEx) {
        Training training = trainingDao.findByName(trainingName);
        User user = User.builder()
                .id(userDto.getId())
                .build();

        Integer completePercent = doneEx * 100 / training.getExercises().size();

        if (completePercent > 0) {
            userTrainingDao.save(
                    UserTraining.builder()
                            .date(LocalDate.now())
                            .user(user)
                            .training(training)
                            .completePercent(completePercent)
                            .build()
            );
        }
    }

    private List<TrainingDto> trainingToTrainingDto(UserDto user, Function<Integer, List<Training>> function) {
        Integer lvl = LevelService.getLvl(userDao.findById(user.getId()).getXp());
        return function.apply(lvl).stream()
                .map(training -> TrainingDto.builder()
                        .name(training.getName())
                        .complexity(training.getComplexity())
                        .type(training.getType())
                        .build())
                .collect(Collectors.toList());
    }
}
