package com.example.controller;

import com.example.common.entity.Result;
import com.example.common.enums.StatusCode;
import com.example.common.exception.CommonException;
import com.example.common.utils.ResultUtils;
import com.example.domain.UserDTO;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author wangqin
 * @version 1.0
 * @date 2019/11/24 21:02
 */
@RestController
@RequestMapping("/api/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/login")
    public Result login(HttpServletRequest request, String userName, String password)
    {
        UserDTO user = userService.findUserByUserNameAndPassword(userName, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginUserId", user.getId());
            redisTemplate.opsForValue().set("loginUser:" + user.getId(), session.getId());

            return ResultUtils.success();
        } else
        {
            throw new CommonException(StatusCode.ACCOUNT_OR_PASSWORD_ERROR);
        }
    }

    @RequestMapping(value = "/getUserInfo")
    public Result get(long userId)
    {
        UserDTO user = userService.findUserByUserId(userId);
        if (user != null)
        {
            return ResultUtils.success();
        }
        else
        {
            throw new CommonException(StatusCode.USER_NOT_EXIST);
        }
    }

}
