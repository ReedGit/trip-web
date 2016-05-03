package com.bysj.dao.impl;

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
        return (delete == 0 ? false : true);
    }

    @SuppressWarnings("unchecked")
    @Override
    public PageBean<Travel> findByPage(int page, int size, List<Long> ids) {
        PageBean<Travel> pageBean = new PageBean<>(page, size);
        StringBuilder hql = new StringBuilder();
        hql.append("select distinct t from Travel t where t.travelId in :ids");
        hql.append("order by t.createTime desc");

        List<Travel> travels = getSession().createQuery(hql.toString())
                .setFirstResult((page - 1) * size).setMaxResults(size).list();

        pageBean.setList(travels);
        pageBean.setTotal(getTotal(ids));

        return pageBean;
    }
    
    @Override
    public int getTotalByUser(long userId) {
        StringBuilder hql = new StringBuilder();
        hql.append("select count(*) from Travel c ")
           .append(" where c.userId = :userId ");
        Object total = getSession().createQuery(hql.toString())
                .setLong("userId", userId).uniqueResult();
        return Integer.parseInt(total.toString());
    }

    public int getTotal(List<Long> ids){
        StringBuilder hql = new StringBuilder();
        hql.append("select distinct t from Travel t where t.travelId in :ids");
        int total = getSession().createQuery(hql.toString()).list().size();
        return total;
    }

    @SuppressWarnings("unchecked")
    @Override
    public PageBean<Travel> findByUser(int page, int size, long userId) {
        PageBean<Travel> pageBean = new PageBean<>(page, size);
        StringBuilder hql = new StringBuilder();
        hql.append("select distinct t from Travel t where t.userId = :userId ");
        hql.append("order by t.createTime desc");

        List<Travel> travels = getSession().createQuery(hql.toString())
                .setParameter("userId", userId)
                .setFirstResult((page - 1) * size).setMaxResults(size).list();

        pageBean.setList(travels);
        pageBean.setTotal(getTotalByUser(userId));

        return pageBean;
    }
}
