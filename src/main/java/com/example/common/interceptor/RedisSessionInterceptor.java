package com.example.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 拦截登录失效的请求
 * @author wangqin
 * @version 1.0
 * @date 2019/11/27 10:32
 */
public class RedisSessionInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录验证
        HttpSession session = request.getSession();
        Object loginUserId = session.getAttribute("loginUserId");
        if (loginUserId != null) {
            try {
                //redis中获取session
                String loginSessionId = redisTemplate.opsForValue().get("loginUser:" + (long) loginUserId);
                if(loginSessionId != null && loginSessionId.equals(session.getId())){
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response401(response);
        return false;
    }

    private void response401(HttpServletResponse response)
    {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        try
        {
            response.getWriter().print(JSON.toJSONString(new Result("401","用户未登录")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
