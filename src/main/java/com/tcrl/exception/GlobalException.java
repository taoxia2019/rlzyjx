package com.tcrl.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName GlobalException
 * @Description DOTO
 * @Author Administrator
 * @Date 2019/9/29 9:22
 * @Version 1.0
 */
@Configuration
public class GlobalException implements HandlerExceptionResolver{
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv=new ModelAndView();
        if(e instanceof RuntimeException){
            mv.setViewName("error");
            mv.addObject("error",e.getMessage().toString()+"...");
        }

        return mv;
    }
}
