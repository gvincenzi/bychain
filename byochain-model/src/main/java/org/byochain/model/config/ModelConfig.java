
package org.byochain.model.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Class for Annotation Type Configuration
 * @author Giuseppe Vincenzi
 *
 */
@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = { "org.byochain.model" })
@EnableJpaRepositories("org.byochain.model.repository")
@Profile("production")
public class ModelConfig {
	@Autowired
	private Environment env;
	
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(env.getRequiredProperty("db.jdbcurl"), env.getRequiredProperty("db.username"),env.getRequiredProperty("db.password"));
		dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
		return dataSource;
	}

}