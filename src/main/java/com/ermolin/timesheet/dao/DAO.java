package com.ermolin.timesheet.dao;

public interface DAO<T> {
    void insert(T t);
    void update(T t);
    void delete(T t);
}
