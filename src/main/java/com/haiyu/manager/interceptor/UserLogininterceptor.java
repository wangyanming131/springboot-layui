package com.haiyu.manager.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 设置session失效统一调整路径
 */
@Component
@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(true);
        String username = (String) session.getAttribute("user");
        if (username != null) {
            // 已登录
            return true;
        } else {
            // 未登录或登录失效,跳转到登录页面
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }
    }
}
