package com.bysj.dao.impl;

import org.springframework.stereotype.Repository;

import com.bysj.dao.UserDao;
import com.bysj.model.User;

/**
 * 
* <p>Title: UserDaoImpl</p>
* <p>Description: 用户模块Dao</p>
* <p>Copyright: Copyright (c) 2016</p>
* @author zerolu
* @date 2016年3月21日
* @version 1.0
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

    /**
     * 
     */
    @Override
    public User findByUsername(String email) {
        StringBuilder hql = new StringBuilder();
        hql.append("select u from User u where u.email = :email");
        User user = (User)this.getSession().createQuery(hql.toString())
                        .setString("email", email)
                        .uniqueResult();
        return user;
    }

    @Override
    public User findByNameAndPass(String email, String password) {
        StringBuilder hql = new StringBuilder();
        hql.append("select u from User u where u.email = :email and u.password = :password");
        User user = (User)this.getSession().createQuery(hql.toString())
                        .setString("email", email)
                        .setString("password", password)
                        .uniqueResult();
        return user;
    }

    @Override
    public User findByIdAndToken(long id, String token) {
        StringBuilder hql = new StringBuilder();
        hql.append("select u from User u where u.userId = :userId and u.token = :token");
        User user = (User)this.getSession().createQuery(hql.toString())
                        .setLong("userId", id)
                        .setString("token", token)
                        .uniqueResult();
        return user;
    }

}
