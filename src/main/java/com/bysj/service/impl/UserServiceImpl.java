package com.bysj.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bysj.dao.UserDao;
import com.bysj.model.User;
import com.bysj.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

    @Resource(name="userDao")
    private UserDao userDao;
    
    /**
     * 添加用户
     * @param user
     */
    public void saveUser(User user){
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findByNameAndPass(String email, String password) {
        return userDao.findByNameAndPass(email, password);
    }

    @Override
    public User findById(long id) {
        return userDao.findById(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public User findByIdAndToken(long id, String token) {
        return userDao.findByIdAndToken(id, token);
    }
    
}
