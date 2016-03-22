package com.bysj.dao.impl;

import java.lang.reflect.ParameterizedType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bysj.dao.BaseDao;

/**
 * 
* <p>Title: BaseDaoImpl</p>
* <p>Description: 所有Dao的基类</p>
* <p>Copyright: Copyright (c) 2016</p>
* @author zerolu
* @date 2016年3月21日
* @version 1.0
 */
public class BaseDaoImpl<T> implements BaseDao<T>{

    @Autowired
    private SessionFactory sessionFactory;
    protected Class<T> clazz;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public BaseDaoImpl() {
        ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        this.clazz = (Class) type.getActualTypeArguments()[0];
    }
    
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T entity) {
        getSession().save(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findById(long id) {
        return (T) getSession().get(clazz, id);
    }

    @Override
    public void update(T t) {
        getSession().update(t);
        getSession().flush();
    }

    
}
