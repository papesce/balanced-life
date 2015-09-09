package com.balance.gym.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringWebMvcInitializer  extends AbstractAnnotationConfigDispatcherServletInitializer {
		  
		  @Override
		  protected Class<?>[] getRootConfigClasses() {
			  return new Class[] { BalancedSecurityConfig.class };
		  }
		 
		  @Override
		  protected Class<?>[] getServletConfigClasses() {
		    return new Class[] {SpringRootConfig.class};
		  }
		 
		  @Override
		  protected String[] getServletMappings() {
		    return new String[]{"/"};
		  }
		 
	



}