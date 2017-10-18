package ru.itis.dao;

import ru.itis.entity.User;

public interface UserDao extends BaseDao<User, Long> {
    User findByLogin(String login);
}
