package com.balance.gym.config;


/**
 * this file replaces the need for a web.xml file
 */
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.balance.gym.Application;



@Configuration
@ComponentScan(basePackageClasses = Application.class)

public class SpringRootConfig {

}