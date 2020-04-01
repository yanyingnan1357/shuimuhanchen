package cn.yyn.web.config;

import cn.yyn.web.interceptor.BackInterceptor;
import cn.yyn.web.interceptor.ForeInterceptor;
import com.alibaba.druid.util.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;

/**
 * 说明：更改编码为UTF-8
 *
 * @author:yyn
 */
@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    @Bean
    public HandlerInterceptor getForeInterceptor() {
        return new ForeInterceptor();
    }

    @Bean
    public HandlerInterceptor getBackInterceptor() {
        return new BackInterceptor();
    }

    @Bean//也可以用这种方式注入，自动配置原理所有bean会一起生效
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override//页面控制
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            @Override//拦截器添加
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(getBackInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html", "/", "/user/login");
                registry.addInterceptor(getForeInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/sys/**", "/js/**", "/css/**", "/img/**");
            }
        };
    }

    @Bean//中英文切换控制
    public LocaleResolver localeResolver(){
        return new LocaleResolver(){
            @Override
            public Locale resolveLocale(HttpServletRequest request) {
                String lan = request.getParameter("lan");
                Locale locale = Locale.getDefault();
                if(!StringUtils.isEmpty(lan)){
                    String[] split = lan.split("_");
                    locale = new Locale(split[0], split[1]);
                }
                return locale;
            }
            @Override
            public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

            }
        };
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

}

