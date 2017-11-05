package ru.itis.dao;

import ru.itis.entity.UserTraining;

import java.util.List;

/**
 * 04.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface UserTrainingDao extends CrudDao<UserTraining, Integer> {
    List<UserTraining> findByUser(Long userId);
}
