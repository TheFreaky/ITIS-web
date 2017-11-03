package ru.itis.dao;

import ru.itis.entity.Exercise;

/**
 * 30.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface ExerciseDao extends CrudDao<Exercise, Integer> {
    Exercise findByName(String name);
}
