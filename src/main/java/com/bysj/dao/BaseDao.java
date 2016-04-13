package com.bysj.dao;

import com.bysj.model.PageBean;

public interface BaseDao<T> {
    
    public T save(T t);

    public T findById(long id);
    
    public void update(T t);
    
    public PageBean<T> findAllByPage(int page , int size , String hql);
    
    public int getTotal();
    
}
