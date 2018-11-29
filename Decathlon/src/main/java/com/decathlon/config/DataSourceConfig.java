package com.decathlon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataSourceConfig {
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String passwrod;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;
	
	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String dialect;
	
	@Value("${spring.jpa.show-sql}")
	private String showSql;
	
	@Value("${spring.jpa.hibernate.naming-strategy}")
	private String namingStragey;
	
	
	
	
}
