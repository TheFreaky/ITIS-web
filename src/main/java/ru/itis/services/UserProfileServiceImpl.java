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
public class UserProfileServiceImpl implements UserProfileService {
    private final UserDao userDao;
    private final UserTrainingDao userTrainingDao;

    public UserProfileServiceImpl(UserDao userDao, UserTrainingDao userTrainingDao) {
        this.userDao = userDao;
        this.userTrainingDao = userTrainingDao;
    }

    @Override
    public UserProfileDto getUserProfile(Long id) {
        User user = userDao.find(id);
        List<UserTraining> userTrainings = userTrainingDao.findByUserId(id);

        Long xp = user.getXp();
        Integer lvl = UserLevelUtil.getLvl(xp);
        Long xpToLvlUp = UserLevelUtil.getXpForLvl(lvl + 1);
        Long xpToCurrentLvl = UserLevelUtil.getXpForLvl(lvl);
        long progress = (xp - xpToCurrentLvl) * 100 / (xpToLvlUp - xpToCurrentLvl);

        int strengthLvl = user.getStrength() / 100;
        int strengthProgress = user.getStrength() % 100;
        int staminaLvl = user.getStamina() / 100;
        int staminaProgress = user.getStamina() % 100;
        int flexibilityLvl = user.getFlexibility() / 100;
        int flexibilityProgress = user.getFlexibility() % 100;



        return UserProfileDto
                .builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .weight(user.getWeight())
                .height(user.getHeight())
                .specialization(user.getSpecialization())
                .xp(xp)
                .progress((byte) progress)
                .lvl(lvl)
                .xpToLvlUp(xpToLvlUp)
                .strengthLvl((short) strengthLvl)
                .strengthProgress((short) strengthProgress)
                .staminaLvl((short) staminaLvl)
                .staminaProgress((short) staminaProgress)
                .flexibilityLvl((short) flexibilityLvl)
                .flexibilityProgress((short) flexibilityProgress)
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
