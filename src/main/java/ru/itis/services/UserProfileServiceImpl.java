package ru.itis.services;

import ru.itis.dao.UserDao;
import ru.itis.dao.UserTrainingDao;
import ru.itis.dto.UserDto;
import ru.itis.dto.UserProfileDto;
import ru.itis.dto.UserProfileForm;
import ru.itis.models.User;
import ru.itis.models.UserTraining;
import ru.itis.utils.LevelUtil;

import java.util.HashMap;
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
        User user = userDao.find(id);
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
                .strengthLvl((Short) strengthDetails.get("lvl"))
                .strengthProgress((Short) strengthDetails.get("progress"))
                .staminaLvl((Short) staminaDetails.get("lvl"))
                .staminaProgress((Short) staminaDetails.get("progress"))
                .flexibilityLvl((Short) flexibilityDetails.get("lvl"))
                .flexibilityProgress((Short) flexibilityDetails.get("progress"))
                .gender(user.getGender())
                .userTrainings(userTrainings)
                .build();
    }


    @Override
    public UserDto editUserProfile(UserProfileForm profile, UserDto userDto) {
        User model = User.builder()
                .id(userDto.getId())
                .name(profile.getName())
                .weight(Float.valueOf(profile.getWeight()))
                .height(Short.valueOf(profile.getHeight()))
                .gender(Boolean.valueOf(profile.getGender()))
                .build();
        userDao.save(model);
        return UserDto.builder()
                .id(userDto.getId())
                .name(model.getName())
                .build();
    }

    private Map<String, ? super Number> getXpDetails(Long xp) {
        Map<String, ? super Number> xpDetails = new HashMap<>();

        Integer lvl = LevelUtil.getLvl(xp);
        Long xpToCurrentLvl = LevelUtil.getXpForLvl(lvl);
        Long xpToLvlUp = LevelUtil.getXpForLvl(lvl + 1);
        Integer progress = Math.toIntExact((xp - xpToCurrentLvl) * 100 / (xpToLvlUp - xpToCurrentLvl));

        xpDetails.put("lvl", lvl);
        xpDetails.put("lvl up", xpToLvlUp);
        xpDetails.put("progress", progress);
        return xpDetails;
    }
}
