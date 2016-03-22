package com.bysj.dao;

import com.bysj.model.User;

public interface UserDao extends BaseDao<User>{

    public User findByUsername(String email);
    public User findByNameAndPass(String email,String password);
    public User findByIdAndToken(long id,String token);
}
