package com.bysj.service;

import com.bysj.model.User;

public interface UserService {

    public void saveUser(User user);
    public User findByUsername(String username);
    public User findByNameAndPass(String email,String password);
    public User findById(long id);
    public void updateUser(User user);
    public User findByIdAndToken(long id,String token);
}
