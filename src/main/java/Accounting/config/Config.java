package Accounting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName:Config
 * @Description:将拦截器注册到适配器中
 * @Author:liumengying
 * @Date: 2023/3/23 17:14
 * Version v1.0
 */
@Configuration
public class Config implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new Handle()).addPathPatterns("/**");
    }
}
