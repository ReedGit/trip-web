package com.bysj.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bysj.dao.BaseDao;
import com.bysj.model.PageBean;

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
    public T save(T entity) {
        getSession().save(entity);
        return entity;
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

    @SuppressWarnings("unchecked")
    @Override
    public PageBean<T> findAllByPage(int page, int size, String hql) {
        PageBean<T> pageBean = new PageBean<T>(page,size);
        StringBuilder from = new StringBuilder();
        from.append("from ").append(clazz.getSimpleName()).append(hql.toString());
        List<T> list = getSession().createQuery(from.toString())
                .setFirstResult((page - 1) * size)
                .setMaxResults(size).list();
        pageBean.setList(list);
        pageBean.setTotal(getTotal());
        return pageBean;
    }

    @Override
    public int getTotal() {
        StringBuilder hql = new StringBuilder();
        hql.append("select count(*) from ").append(clazz.getSimpleName());
        Object total = getSession().createQuery(hql.toString()).uniqueResult();
        return Integer.parseInt(total.toString());
    }

}
