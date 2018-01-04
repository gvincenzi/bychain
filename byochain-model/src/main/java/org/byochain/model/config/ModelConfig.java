package org.byochain.model.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Spring Boot configuration class to define dataSource for "production" profile.
 * @author Giuseppe Vincenzi
 *
 */
@Configuration
@ComponentScan(basePackages = { "org.byochain.model" })
@EnableJpaRepositories("org.byochain.model.repository")
@Profile("production")
public class ModelConfig {
	@Autowired
	private Environment env;
	
	/**
	 * DataSource loaded by properties in application.properties file 
	 * @return DriverManagerDataSource
	 */
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(env.getRequiredProperty("db.jdbcurl"), env.getRequiredProperty("db.username"),env.getRequiredProperty("db.password"));
		dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
		return dataSource;
	}

}
