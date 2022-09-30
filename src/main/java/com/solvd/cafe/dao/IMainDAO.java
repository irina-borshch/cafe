package com.solvd.cafe.dao;

import java.util.List;

public interface IMainDAO<T> {
    void create(T object);

    void update(T t);

    void delete(int id);

    T getById(int id);

    List<T> getAllRecords();

}
