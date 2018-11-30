package com.decathlon.config;

import java.beans.PropertyVetoException;
import java.util.HashMap;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.decathlon.DecathlonApplication;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = {
		"com.decathlon.repository" }, 
	entityManagerFactoryRef = "primaryEntityManager", transactionManagerRef = "primaryTransactionManager")
@EnableTransactionManagement
public class DataSourceConfig {

	private static final Logger logger = Logger.getLogger(DataSourceConfig.class);

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;

	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String dialect;

	@Value("${spring.jpa.show-sql}")
	private String showSql;

	@Value("${spring.jpa.hibernate.naming-strategy}")
	private String namingStragey;

	@Primary
	@Bean
	public HikariDataSource primaryDataSource() {

		HikariConfig jdbcConfig = new HikariConfig();
		jdbcConfig.setJdbcUrl(url);
		jdbcConfig.setUsername(username);
		jdbcConfig.setPassword(password);

		return new HikariDataSource(jdbcConfig);
	}

	@Primary
	@Bean
	public PlatformTransactionManager primaryTransactionManager() throws PropertyVetoException {
		logger.info("Inside primaryTransactionManager");

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(primaryEntityManager().getObject());
		return transactionManager;
	}

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean primaryEntityManager() throws PropertyVetoException {
		logger.info("Inside primaryEntityManager");

		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(primaryDataSource());
		em.setPersistenceUnitName("primaryDataSource");

		em.setPackagesToScan(new String[] { "com.decathlon" });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.hbm2ddl.auto", ddlAuto);
		properties.put("hibernate.show_sql", showSql);

		em.setJpaPropertyMap(properties);

		return em;
	}
}
