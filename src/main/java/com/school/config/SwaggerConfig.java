package com.school.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfig {
	// 아래 부분 설정 해서 할려고 했으나 안되서 주석 처리 
	//@Value("${url.swaggerProtocols}")
	//String swaggerProtocols;
	//@Value("${url.swaggerBaseUrl}")
	//String swaggerBaseUrl;
	
    @Bean
    public Docket swaggerSpringMvcPlugin() {
    	Set protocols = new HashSet();
    	
    	String swaggerProtocols = "http";
    	String swaggerBaseUrl = "";
    	
    	protocols.add(swaggerProtocols);
    	
    	Docket docker = new Docket(DocumentationType.SWAGGER_2);
    	
    	if(swaggerBaseUrl.length() > 2) {
    		docker.host(swaggerBaseUrl);
    	}
    	
    	return docker.protocols(protocols)
    			.host(swaggerBaseUrl)
    			.select()                                      
    			.apis(RequestHandlerSelectors.basePackage("com.school"))
    			.build();
    }   
}    
