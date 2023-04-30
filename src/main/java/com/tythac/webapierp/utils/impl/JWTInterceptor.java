package com.tythac.webapierp.utils.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CXY
 * @version Create Time: 2023/4/6
 * @Description JWT 攔截器(不要透過 interface、會出錯)
 */
@Component
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 用這樣宣告、不要透過 interface、會出錯
        JWTUtilsImpl jwtUtilsImpl = new JWTUtilsImpl();
        // get Token of Header
        String token = request.getHeader("token");

        try {
            // 驗證 Token
//            jwtUtilsImpl.verifyToken(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 將 map 轉成 json
        // String json = new ObjectMapper().writeValueAsString(map);
        String json = "{\"state\" : false }";
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
//        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
