package com.ssafit.pjt.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssafit.pjt.model.dao")

public class DBconfig {

}
