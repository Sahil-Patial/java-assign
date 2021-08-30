package com.sahil.multitenancy.web.interceptors;

import com.sahil.multitenancy.core.ThreadLocalStorage;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TenantNameInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String tenantName = request.getHeader("X-TenantID");

        ThreadLocalStorage.setTenantName(tenantName);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        ThreadLocalStorage.setTenantName(null);
    }
}
