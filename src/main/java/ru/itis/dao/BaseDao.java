package ru.itis.dao;

import java.util.List;

public interface BaseDao<T, I> {
    void save(T model);
    T find(I id);
    void update(T model);
    void delete(I id);
    List<T> findAll();
}
