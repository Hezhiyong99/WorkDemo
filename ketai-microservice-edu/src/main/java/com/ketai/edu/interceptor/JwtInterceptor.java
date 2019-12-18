package com.ketai.edu.interceptor;

import com.bdqn.common.constants.ResultCodeEnum;
import com.bdqn.common.exception.KetaiException;
import com.ketai.edu.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor extends HandlerInterceptorAdapter {
    /*校验token是否有效*/
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("X-Token");
        System.out.println("‐‐‐‐" + authHeader);
        if (authHeader == null) {
            throw new KetaiException(ResultCodeEnum.USERUNLOGIN_ERROR);
        }
        //验证token
        Claims claims = JwtUtil.checkToken(authHeader);
        return true;
    }
}
