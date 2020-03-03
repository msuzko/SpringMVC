package com.mec.spring.interceptors;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimeInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = Logger.getLogger(TimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime",System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = Long.parseLong(request.getAttribute("startTime").toString());
        long totalTime = System.currentTimeMillis()-startTime;
        try {
            modelAndView.addObject("time", totalTime);
        } catch (Exception e) {
          //  e.printStackTrace();
        }
        logger.info("Time: "+totalTime+" ms");


    }
}
