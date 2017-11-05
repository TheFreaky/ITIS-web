package ru.itis.dao;

import ru.itis.models.Training;

/**
 * 01.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface TrainingDao extends CrudDao<Training, Integer> {
    Training findByName(String name);
}
