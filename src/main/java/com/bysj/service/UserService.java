package com.bysj.service;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.bysj.model.User;

public interface UserService {

    public void saveUser(User user);
    public User findByUsername(String username);
    public User findByNameAndPass(String email,String password);
    public User findById(long id);
    public void updateUser(User user);
    public User findByIdAndToken(long id,String token);
    public JSONObject saveImage(Long id , MultipartFile file,String fileParentDirPath);
}
