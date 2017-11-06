package ru.itis.dao;

import ru.itis.models.Training;

import java.util.List;

/**
 * 01.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public interface TrainingDao extends CrudDao<Training, Integer> {
    Training findByName(String name);
    List<Training> findAllByMinLvlLessThan(Integer lvl);
    //ToDo: order by complexity and type methods
    //ToDo: trainings by min lvl
}
