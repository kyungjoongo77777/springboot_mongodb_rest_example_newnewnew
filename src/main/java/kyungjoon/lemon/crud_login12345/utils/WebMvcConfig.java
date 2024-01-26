package kyungjoon.lemon.crud_login12345.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor())
                .excludePathPatterns("/css/**", "/images/**", "/js/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 모든 경로에 대해 CORS를 허용하는 설정
        registry.addMapping("/**")
                .allowedOrigins("*")  // 허용할 오리진 설정
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // 허용할 HTTP 메서드 설정
                .allowedHeaders("*");  // 허용할 헤더 설정
    }

}
