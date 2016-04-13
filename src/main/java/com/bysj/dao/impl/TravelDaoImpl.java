package com.bysj.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bysj.dao.TravelDao;
import com.bysj.model.PageBean;
import com.bysj.model.Travel;

@Repository(value = "travelDao")
public class TravelDaoImpl extends BaseDaoImpl<Travel> implements TravelDao {

    public int getTotal(Object... params) {
        StringBuilder hql = new StringBuilder();
        hql.append("select distinct t from Travel t , Content c ")
                .append(" where t.travelId = c.travelId ");
        StringBuilder andSql = new StringBuilder();
        for (Object param : params) {
            andSql.append(" and c.location like '%").append(param)
                    .append("%' ");
        }
        hql.append(andSql);
        int total = getSession().createQuery(hql.toString()).list().size();
        return total;
    }

    @Override
    public PageBean<Travel> findAllByPage(int page, int size) {
        StringBuilder hql = new StringBuilder();
        hql.append(" order by createTime desc ");
        return findAllByPage(page, size, hql.toString());
    }

    @SuppressWarnings("unchecked")
    @Override
    public PageBean<Travel> findByArea(int page, int size, String... params) {
        PageBean<Travel> pageBean = new PageBean<>(page, size);
        StringBuilder hql = new StringBuilder();
        hql.append("select distinct t from Travel t , Content c ")
                .append(" where t.travelId = c.travelId ");
        StringBuilder andSql = new StringBuilder();
        for (Object param : params) {
            andSql.append(" and c.location like '%").append(param)
                    .append("%' ");
        }
        hql.append(andSql).append("order by t.createTime desc");

        List<Travel> travels = getSession().createQuery(hql.toString())
                .setFirstResult((page - 1) * size).setMaxResults(size).list();

        pageBean.setList(travels);
        pageBean.setTotal(getTotal((Object[]) params));

        return pageBean;
    }

    @SuppressWarnings("unchecked")
    @Override
    public PageBean<Travel> findByTitle(int page, int size, String... params) {
        PageBean<Travel> pageBean = new PageBean<>(page, size);
        StringBuilder hql = new StringBuilder();
        hql.append("select distinct t from Travel t ");
        StringBuilder andSql = new StringBuilder();
        for (Object param : params) {
            andSql.append(" and t.title like '%").append(param).append("%' ");
        }
        hql.append(andSql).append("order by t.createTime desc");

        List<Travel> travels = getSession().createQuery(hql.toString())
                .setFirstResult((page - 1) * size).setMaxResults(size).list();
        pageBean.setList(travels);
        pageBean.setTotal(getTotal((Object[]) params));
        return pageBean;
    }

    @Override
    public Travel findByTitle(String title) {
        StringBuilder hql = new StringBuilder();
        hql.append(" from Travel t ").append(" where t.title = :title");
        Travel travel = (Travel) getSession().createQuery(hql.toString())
                .setString("title", title).uniqueResult();
        return travel;
    }

    @Override
    public boolean deleteTravel(long id) {
        StringBuilder hql = new StringBuilder();
        hql.append("delete from Travel t ").append(" where t.travelId = :id");
        int delete = getSession().createQuery(hql.toString())
                .setLong("id", id).executeUpdate();
        return (delete == 0 ? true : false);
    }

}
