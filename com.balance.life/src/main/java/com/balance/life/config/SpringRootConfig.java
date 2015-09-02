package com.balance.life.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.balance.life.Application;

@Configuration
@ComponentScan(basePackageClasses = Application.class)

public class SpringRootConfig {

}