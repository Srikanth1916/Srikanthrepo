package io.skyhive.veneer.typeahead;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * The type Training application.
 * 
 * @author krishna
 * @created 13/01/22
 * @project skyhive -veeneer
 */
@SpringBootApplication(scanBasePackages = {"io.skyhive.veneer.typeahead"},
        exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
                ElasticsearchRestClientAutoConfiguration.class})
@OpenAPIDefinition(info =
@Info(title = "Trainings API", version = "1.0", description = "Documentation Trainings API v1.0")
)
@PropertySources({
        @PropertySource("classpath:application-common.yml"),
        @PropertySource("classpath:application-prod.yml")
})
@EnableReactiveMongoRepositories
public class TypeaheadApplication {
    /**
     * The constant NAME.
     */
    protected static final String NAME = "TypeAhead API";

    /**
     * Custom open api open api.
     *
     * @param appVersion the app version
     * @return the open api
     */
    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI()
                .components(new Components())
                .info(new io.swagger.v3.oas.models.info.Info().title(NAME).version(appVersion)
                        .license(new License().name("Apache 2.0").url("http" +
                                "://springdoc.org")));
    }

    /**
     * Cors configurer web mvc configurer.
     *
     * @return the web mvc configurer
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowedOrigins("*");
            }
        };
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(TypeaheadApplication.class, args);
    }
}
