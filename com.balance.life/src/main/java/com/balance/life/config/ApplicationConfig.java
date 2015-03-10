package com.balance.life.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.mobile.device.site.SitePreferenceHandlerInterceptor;
import org.springframework.mobile.device.site.SitePreferenceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.view.LiteDeviceDelegatingViewResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
//@ComponentScan({ "com.balance.life" })

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ImportResource({ "classpath:jpaConfig.xml" })
public class ApplicationConfig extends WebMvcConfigurerAdapter { 


	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DeviceResolverHandlerInterceptor());
        registry.addInterceptor(new SitePreferenceHandlerInterceptor());
    }
	
	@Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new DeviceHandlerMethodArgumentResolver());
        argumentResolvers.add(new SitePreferenceHandlerMethodArgumentResolver());
	}
	
//	@Bean
//    public ViewResolver viewResolver() {
//InternalResourceViewResolver viewResolver = new InternalResourceViewResolver(); viewResolver.setPrefix("/WEB-INF/views/");
//viewResolver.setSuffix(".jsp");
//viewResolver.setOrder(2);
//        return viewResolver;
//    }
//    @Bean
//    public ViewResolver mobileViewResolver() {
//        LiteDeviceDelegatingViewResolver delegatingViewResolver =
//            new LiteDeviceDelegatingViewResolver(viewResolver());
//        delegatingViewResolver.setOrder(1);
//        delegatingViewResolver.setMobilePrefix("mobile/");
//        delegatingViewResolver.setTabletPrefix("tablet/");
//        return delegatingViewResolver;
//}
	
//	@Bean
//    public ViewResolver internalResourceViewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/views/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//}
	
	@Bean
	public LiteDeviceDelegatingViewResolver liteDeviceAwareViewResolver() {
	    InternalResourceViewResolver delegate = 
	            new InternalResourceViewResolver();
	    delegate.setPrefix("/WEB-INF/views/");
	    delegate.setSuffix(".jsp");
	    LiteDeviceDelegatingViewResolver resolver = 
	            new LiteDeviceDelegatingViewResolver(delegate);
	    resolver.setMobilePrefix("mobile/");
	    resolver.setTabletPrefix("tablet/");
	    return resolver;
	}

}
