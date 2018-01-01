package org.byochain.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@PropertySource({"classpath:build.properties"})
@EnableSwagger2
public class SwaggerConfig {   
	@Value("${application.name}")
	private String applicationName;

	@Value("${build.version}")
	private String buildVersion;
	
	@Autowired
    private Environment environment;
	
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.ant("/byochain/**"))                          
          .build()                                           
          .apiInfo( getMetadata() );
    }

    private ApiInfo getMetadata() {
        return new ApiInfoBuilder()
                .title( "REST API BYOChain : " + applicationName )
                .description( "REST API for OpenSource BlockChain project BYOChain" )
                .version( environment.getActiveProfiles()[0].toUpperCase() + " v" + buildVersion )
                .build();
    }
}