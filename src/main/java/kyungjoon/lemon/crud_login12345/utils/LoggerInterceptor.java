package kyungjoon.lemon.crud_login12345.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("==================== BEGIN ====================");

        String token1= request.getHeader("Authorization");
        System.out.println(token1);

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("==================== End ====================");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

}
