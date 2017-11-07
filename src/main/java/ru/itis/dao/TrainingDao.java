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

    List<Training> findAllByMinLvlLessThanOrderByComplexity(Integer lvl);

    List<Training> findAllByMinLvlLessThanOrderByType(Integer lvl);

}
