package com.bysj.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bysj.dao.CollectionDao;
import com.bysj.dao.LikeDao;
import com.bysj.dao.TravelDao;
import com.bysj.dao.UserDao;
import com.bysj.dto.UserDto;
import com.bysj.model.User;
import com.bysj.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

    @Resource(name="userDao")
    private UserDao userDao;

    @Resource(name = "likeDao")
    private LikeDao likeDao;
    
    @Resource(name = "travelDao")
    private TravelDao travelDao;
    
    @Resource(name = "collectionDao")
    private CollectionDao collectionDao;
    
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
    public String saveImage(Long id , MultipartFile file,String fileParentDirPath) {
        String fileOriginName = file.getOriginalFilename();
        String imagePath = null;
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
                imagePath = fileSubDirPath.toString() + "\\" + fileName;
                imagePath = imagePath.replaceAll("\\\\", "/");
            } catch (IllegalStateException e) {
            } catch (IOException e) {
            }
        } else {
        }
        return imagePath;
    }

    @Override
    public UserDto findByUserId(long userId) {
        User user = findById(userId);
        UserDto userDto = null;
        if (user != null) {
            userDto = new UserDto(user);
            userDto.setToken(null);
            userDto.setCollection(collectionDao.getTotalByUser(userId));
            userDto.setTravels(travelDao.getTotalByUser(userId));
            userDto.setLiked(likeDao.getTotalByUser(userId));
        }
        return userDto;
    }
    
}
