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

    List<Training> findAllByOrderByComplexityAsc();

    List<Training> findAllByOrderByComplexityDesc();

    List<Training> findAllByOrderByTypeAsc();

    List<Training> findAllByOrderByTypeDesc();
}
