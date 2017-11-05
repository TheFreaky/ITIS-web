package ru.itis.dao;

import ru.itis.models.User;

public interface UserDao extends CrudDao<User, Long> {
    User findByLogin(String login);
}
