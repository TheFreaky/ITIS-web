package ru.itis.dao;

import java.util.List;

public interface CrudDao<T, I> {
    void save(T model);
    T findById(I id);
    void update(T model);
    void delete(I id);
    List<T> findAll();
}
