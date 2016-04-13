package com.bysj.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.bysj.dao.UserDao;
import com.bysj.dto.UserDto;
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
    
    @Override
    public JSONObject saveImage(Long id , MultipartFile file,String fileParentDirPath) {
        JSONObject result = new JSONObject();
        String fileOriginName = file.getOriginalFilename();
        if (fileOriginName.endsWith(".jpg")
                || fileOriginName.endsWith(".png")
                || fileOriginName.endsWith(".JPG")
                || fileOriginName.endsWith(".PNG")
                || fileOriginName.endsWith(".gif")
                || fileOriginName.endsWith(".GIF")) {
            StringBuilder fileSavePath = new StringBuilder();
            StringBuilder fileSubDirPath = new StringBuilder()
                    .append("\\upload\\").append(id);
            File fileDir = new File(fileParentDirPath + fileSubDirPath.toString());
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            StringBuilder fileName = new StringBuilder()
                    .append(UUID.randomUUID().toString())
                    .append(fileOriginName.substring(fileOriginName.lastIndexOf(".")));
            
            fileSavePath.append(fileParentDirPath)
                        .append(fileSubDirPath)
                        .append("\\")
                        .append(fileName);
            
            File fileSave = new File(fileSavePath.toString());
            try {
                file.transferTo(fileSave);
                String imagePath = fileSubDirPath.toString() + "\\" + fileName;
                imagePath = imagePath.replaceAll("\\\\", "/");
                User user = findById(id);
                
                user.setHeadImage(imagePath);
                
                this.updateUser(user);
                UserDto userDto = new UserDto(user);
                result.put("status", "0");
                result.put("msg", "图片上传成功");
                result.put("data", userDto);
            } catch (IllegalStateException e) {
                result.put("status", "1");
                result.put("msg", e.getMessage());
            } catch (IOException e) {
                result.put("status", "1");
                result.put("msg", e.getMessage());
            }
        } else {
            result.put("status", "1");
            result.put("msg", "图片格式只支持jpg/png/gif!");
        }
        return result;
    }
    
}
