package ru.itis.services;

import ru.itis.dao.UserDao;
import ru.itis.dao.UserTrainingDao;
import ru.itis.dto.UserProfileDto;
import ru.itis.models.User;
import ru.itis.models.UserTraining;
import ru.itis.utils.UserLevelUtil;

import java.util.List;

/**
 * 07.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class UserProfileImpl implements UserProfile {
    private final UserDao userDao;
    private final UserTrainingDao userTrainingDao;

    public UserProfileImpl(UserDao userDao, UserTrainingDao userTrainingDao) {
        this.userDao = userDao;
        this.userTrainingDao = userTrainingDao;
    }

    @Override
    public UserProfileDto getUserProfile(Long id) {
        User user = userDao.find(id);
        List<UserTraining> userTrainings = userTrainingDao.findByUserId(id);
        return UserProfileDto
                .builder()
                .id(user.getId())
                .name(user.getName())
                .weight(user.getWeight())
                .height(user.getHeight())
                .specialization(user.getSpecialization())
                .lvl(UserLevelUtil.getLvl(user.getXp()))
                .strength(user.getStrength())
                .stamina(user.getStamina())
                .flexibility(user.getFlexibility())
                .gender(user.getGender())
                .userTrainings(userTrainings)
                .build();
    }

    @Override
    public UserProfileDto editUserProfile(UserProfileDto profile) {
        User model = User.builder()
                .id(profile.getId())
                .name(profile.getName())
                .weight(profile.getWeight())
                .height(profile.getHeight())
                .gender(profile.getGender())
                .build();
        userDao.save(model);
        return profile;
    }
}
