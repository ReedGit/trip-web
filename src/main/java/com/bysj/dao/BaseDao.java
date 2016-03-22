package com.bysj.dao;

public interface BaseDao<T> {
    
    public void save(T t);

    public T findById(long id);
    
    public void update(T t);
}
