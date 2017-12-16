package ru.itis.services.impl;

import com.google.common.collect.ImmutableMap;
import ru.itis.dao.UserDao;
import ru.itis.dao.UserTrainingDao;
import ru.itis.dto.UserDto;
import ru.itis.dto.UserProfileDto;
import ru.itis.dto.UserProfileForm;
import ru.itis.models.User;
import ru.itis.models.UserTraining;
import ru.itis.services.UserProfileService;

import java.util.List;
import java.util.Map;

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
        User user = userDao.findById(id);
        List<UserTraining> userTrainings = userTrainingDao.findByUserId(id);

        Long xp = user.getXp();
        Map<String, ? super Number> userXpDetails = getXpDetails(xp);
        Map<String, ? super Number> strengthDetails = getXpDetails((long) (user.getStrength() / 10));
        Map<String, ? super Number> staminaDetails = getXpDetails((long) (user.getStamina() / 10));
        Map<String, ? super Number> flexibilityDetails = getXpDetails((long) (user.getFlexibility() / 10));

        return UserProfileDto
                .builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .weight(user.getWeight())
                .height(user.getHeight())
                .specialization(user.getSpecialization())
                .xp(xp)
                .progress((Integer) userXpDetails.get("progress"))
                .lvl((Integer) userXpDetails.get("lvl"))
                .xpToLvlUp((Long) userXpDetails.get("lvl up"))
                .strengthLvl((Integer) strengthDetails.get("lvl"))
                .strengthProgress((Integer) strengthDetails.get("progress"))
                .staminaLvl((Integer) staminaDetails.get("lvl"))
                .staminaProgress((Integer) staminaDetails.get("progress"))
                .flexibilityLvl((Integer) flexibilityDetails.get("lvl"))
                .flexibilityProgress((Integer) flexibilityDetails.get("progress"))
                .gender(user.getGender())
                .userTrainings(userTrainings)
                .xpLastMonth(userDao.findTotalXpLastMonthById(id))
                .build();
    }


    @Override
    public UserDto editUserProfile(UserProfileForm profile, UserDto userDto) {
        User user = userDao.findById(userDto.getId());
        user.setName(profile.getName());
        user.setWeight(Float.valueOf(profile.getWeight()));
        user.setHeight(Short.valueOf(profile.getHeight()));
        user.setGender(Boolean.valueOf(profile.getGender()));
        userDao.update(user);
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    private Map<String, ? super Number> getXpDetails(Long xp) {
        Integer lvl = LevelService.getLvl(xp);
        Long xpToCurrentLvl = LevelService.getXpForLvl(lvl);
        Long xpToLvlUp = LevelService.getXpForLvl(lvl + 1);
        Integer progress = Math.toIntExact((xp - xpToCurrentLvl) * 100 / (xpToLvlUp - xpToCurrentLvl));

        return ImmutableMap.of("lvl", lvl,"lvl up", xpToLvlUp,"progress", progress);
    }
}
