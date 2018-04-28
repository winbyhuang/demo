package com.example.demo.common.interceptor;

import com.example.demo.common.constant.SysConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class SecurityInterceptor implements HandlerInterceptor {
    private static final Logger logger = LogManager.getLogger();
    private static final String REQUEST_URL_DO = ".do";
    private static final String REQUEST_URL_JSON = ".json";
    private static final String REQUEST_REPLACE = "\\-";
    private static final String REQUEST_REMARK = "requestRemark:";
    private static final String REQUEST_IP = ",ip:";
    private static final String REQUEST_URL = ",url:";
    private static final String REQUEST_PARAM = ",param:";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            String url = request.getRequestURI();
            String requestRemark = UUID.randomUUID().toString().toUpperCase().replaceAll(REQUEST_REPLACE, "").toString();
            request.setAttribute(SysConstants.REQUEST_REMARK, requestRemark);
            logger.info(REQUEST_REMARK + requestRemark + REQUEST_IP + request.getRemoteAddr() + REQUEST_URL + url + REQUEST_PARAM + request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}