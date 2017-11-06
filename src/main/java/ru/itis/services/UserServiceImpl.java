package ru.itis.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.dao.UserDao;
import ru.itis.dto.UserDto;
import ru.itis.dto.UserSignInDto;
import ru.itis.models.User;

/**
 * 05.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final BCryptPasswordEncoder encoder;

    static Log log = LogFactory.getLog(BCryptPasswordEncoder.class);

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDto register(ru.itis.dto.UserRegistrationDto user) {
        // 1. Хешировать пароль
        String hashPassword = encoder.encode(user.getPassword());
        // 2. Сохранить в БД
        User model = User.builder()
                .name(user.getName())
                .login(user.getLogin())
                .password(hashPassword)
                .build();
        userDao.save(model);
        // 3. Получить id и имя и вернуть как результат
        return UserDto.builder()
                .id(model.getId())
                .name(model.getName())
                .build();
    }

    @Override
    public UserDto signIn(UserSignInDto user) {
        User model = userDao.findByLogin(user.getLogin());
        if (model != null && encoder.matches(user.getPassword(), model.getPassword())) {
            return UserDto.builder()
                    .id(model.getId())
                    .name(model.getName())
                    .build();
        }
        return null;
    }



}
