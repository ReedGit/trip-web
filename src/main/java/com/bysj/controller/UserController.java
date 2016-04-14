package com.bysj.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.bysj.dto.UserDto;
import com.bysj.model.User;
import com.bysj.service.UserService;
import com.bysj.utils.SecurityUtils;

/**
 * 
* <p>Title: UserController</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2016</p>
* @author zerolu
* @date 2016年3月25日
* @version 1.0
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    public static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource(name = "userService")
    private UserService userService;

    @Resource
    private JavaMailSenderImpl javaMailSender;

    /**
     * 用户注册
     * @param map
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject register(@RequestParam Map<String, Object> map) {
        JSONObject result = new JSONObject();
        String nickname = map.get("nickname").toString().trim();
        String email = map.get("email").toString().trim();
        String password = map.get("password").toString().trim();
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setNickName(nickname);

        if (userService.findByUsername(email) == null) {
            try {
                userService.saveUser(user);
                User savedUser = userService.findByUsername(email);
                UserDto userDto = new UserDto(savedUser);
                result.put("status", "0");
                result.put("msg", "success");
                result.put("data", userDto);
            } catch (Exception e) {
                result.put("status", "1");
                result.put("msg", e.getMessage());
                logger.error(e.getMessage());
            }
        } else {
            result.put("status", "1");
            result.put("msg", "用户名已经被注册！");
            logger.error("用户名已经被注册！");
        }

        return result;
    }

    /**
     * 用户登录
     * @param map
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(@RequestParam Map<String, Object> map,
                            HttpServletRequest request) {
        JSONObject result = new JSONObject();
//        Cookie[] cookies = request.getCookies();
//        if(cookies != null){
//            for(Cookie cookie : cookies){
//                if()
//            }
//        }
        
        String email = map.get("email").toString().trim();
        String password = map.get("password").toString().trim();
//        int autoLogin = 0;
//        if(map.get("autoLogin") != null){
//            autoLogin = Integer.parseInt(map.get(autoLogin).toString());
//        }
        User user = userService.findByNameAndPass(email, password);
        if (user != null) {
            String token = SecurityUtils.getToken();
            user.setToken(token);
            userService.updateUser(user);
            User newUser = userService.findById(user.getUserId());
//            //保存session
//            HttpSession session = request.getSession();
//            session.setAttribute(Constants.USER_KEY, newUser.getEmail());
//            session.setMaxInactiveInterval(Constants.MAX_ACTIVE_TIME);
//            
//            if(autoLogin == 1){
//                Cookie cookie = 
//            }
            UserDto userDto = new UserDto(newUser);
            result.put("status", "0");
            result.put("msg", "success");
            result.put("data", userDto);
        } else {
            result.put("status", "1");
            result.put("msg", "用户名或者密码错误！");
            logger.error("用户名或者密码错误！");
        }
        return result;
    }

    /**
     * 得到用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getUser(@PathVariable long id,
            @RequestParam Map<String, Object> map) {
        JSONObject result = new JSONObject();
        UserDto userDto = userService.findByUserId(id);
        if (userDto != null) {
            result.put("status", "0");
            result.put("msg", "success");
            result.put("data", userDto);
        } else {
            result.put("status", "1");
            result.put("msg", "该用户不存在！");
        }
        return result;
    }

    /**
     * 用户信息编辑
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject editUser(@PathVariable long id,
            @RequestParam Map<String, Object> map) {
        JSONObject result = new JSONObject();
        String token = null;
        if (map.get("token") == null) {
            result.put("status", "1");
            result.put("msg", "非法修改！");
            return result;
        }
        token = map.get("token").toString();
        User user = userService.findByIdAndToken(id, token);
        if (user != null) {

            if (map.get("email") != null)
                user.setEmail(map.get("email").toString());
            if (map.get("introduction") != null)
                user.setIntroduction(map.get("introduction").toString());
            if (map.get("nickname") != null)
                user.setNickName(map.get("nickname").toString());
            if (map.get("sex") != null)
                user.setSex(Integer.parseInt(map.get("sex").toString()));
            if (map.get("newpassword") != null && map.get("password") != null) {
                if (user.getPassword().equals(map.get("password"))) {
                    user.setPassword(map.get("newpassword").toString());
                    userService.updateUser(user);
                    result.put("status", "0");
                    result.put("msg", "已成功修改密码！");
                } else {
                    result.put("status", "1");
                    result.put("msg", "用户密码输入错误！");
                }
                return result;
            }
            userService.updateUser(user);
            User newUser = userService.findById(id);
            UserDto userDto = new UserDto(newUser);
            result.put("status", "0");
            result.put("msg", "用户信息已经成功修改");
            result.put("data", userDto);
        } else {
            result.put("status", "1");
            result.put("msg", "该用户校验出现异常，非法修改信息！");
        }
        return result;
    }

    /**
     * 用户密码重置
     * @param map
     * @return
     */
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject reset(@RequestParam Map<String, Object> map) {
        JSONObject result = new JSONObject();
        String email = map.get("email").toString();
        User user = userService.findByUsername(email);
        if (user != null) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("13813003012@163.com");
                message.setTo(user.getEmail());
                message.setSubject("重置密码邮件通知");
                StringBuilder text = new StringBuilder();
                String newPassword = SecurityUtils.getRandomString(6);
                text.append("您好，用户").append(user.getEmail()).append("的密码已经重置为")
                        .append(newPassword);
                message.setText(text.toString());
                javaMailSender.send(message);
                user.setPassword(SecurityUtils.encode(newPassword));
                userService.updateUser(user);
                result.put("status", "0");
                result.put("msg", "密码重置成功！");
            } catch (Exception e) {
                result.put("status", "1");
                result.put("msg", e.getMessage());
            }
        } else {
            result.put("status", "0");
            result.put("msg", "该用户不存在！");
        }
        return result;
    }

    /**
     * 用户注册邮箱是否存在
     * @param map
     * @return
     */
    @RequestMapping(value = "/exist", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject exist(@RequestParam Map<String, Object> map) {
        JSONObject result = new JSONObject();
        if (map.get("email") != null) {
            String email = map.get("email").toString();
            User user = userService.findByUsername(email);
            if (user != null) {
                result.put("status", "1");
                result.put("msg", "邮箱已被注册！");
            } else {
                result.put("status", "0");
                result.put("msg", "邮箱未被注册！");
            }
        } else {
            result.put("status", "1");
            result.put("msg", "注册邮箱不能为空！");
        }
        return result;
    }

    /**
     * 上传图片
     * @param id
     * @param map
     * @param file
     * @return
     */
    @RequestMapping(value = "/{id}/change_avatar", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject uploadImage(@PathVariable long id,
            @RequestParam Map<String, Object> map,
            @RequestParam(value = "headImage", required = true) MultipartFile file,
            HttpServletRequest request) {
        JSONObject result = new JSONObject();
        String fileParentPath = request.getSession().getServletContext().getRealPath("/");
        String token = null;
        if (map.get("token") == null) {
            result.put("status", "1");
            result.put("msg", "非法修改！");
            return result;
        }
        token = map.get("token").toString();
        User user = userService.findByIdAndToken(id, token);
        if (user != null && !file.isEmpty()) {
            result = userService.saveImage(id,file, fileParentPath);
        } else {
            result.put("status", "1");
            result.put("msg", "上传图片出现错误！");
        }
        return result;
    }

}
