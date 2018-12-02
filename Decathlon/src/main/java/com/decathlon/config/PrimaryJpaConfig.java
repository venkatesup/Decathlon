package com.decathlon.config;

import java.beans.PropertyVetoException;
import java.util.HashMap;

import javax.sql.DataSource;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableJpaRepositories(basePackages = {
		"com.decathlon.repository" }, 
	entityManagerFactoryRef = "primaryEntityManager", transactionManagerRef = "primaryTransactionManager")
@EnableTransactionManagement
public class PrimaryJpaConfig {

	private static final Logger logger = Logger.getLogger(PrimaryJpaConfig.class);

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
	public DataSource primaryDataSource() throws PropertyVetoException {

		logger.info("Inside primaryDataSource");
		final ComboPooledDataSource dataSource = new com.mchange.v2.c3p0.ComboPooledDataSource();
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl(url);
		dataSource.setUser(username);
		dataSource.setPassword(password);
		dataSource.setPreferredTestQuery("Select 1");
		dataSource.setMaxIdleTime(1200);
		dataSource.setMaxIdleTimeExcessConnections(1200);
		dataSource.setMaxConnectionAge(86400);
		return dataSource;

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
