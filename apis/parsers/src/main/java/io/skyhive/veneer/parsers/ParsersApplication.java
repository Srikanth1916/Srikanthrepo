package io.skyhive.veneer.parsers;

import io.skyhive.veneer.common.annotation.InputJsonPropertyIntrospector;
import io.skyhive.veneer.common.annotation.SkyhiveMappingIntrospector;
import io.skyhive.veneer.common.config.VeneerApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The type Resume Parse application.
 */
@SpringBootApplication(scanBasePackages = "io.skyhive.veneer", exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class
})
@OpenAPIDefinition(info =
@Info(title = "Parser API", version = "1.0", description = "Documentation Resume Parse API v1.0")
)
@EnableFeignClients(basePackages = {"io.skyhive.veneer.common.rest", "io.skyhive.veneer.parsers.services"})
@EnableJpaRepositories("io.skyhive.veneer.common")
@EntityScan("io.skyhive.veneer.common.entities")
@PropertySources({
        @PropertySource("classpath:application-common.yml"),
        @PropertySource("classpath:application-prod.yml")
})
@EnableElasticsearchRepositories("io.skyhive.veneer")
//@EnableDatadogMetrics
public class ParsersApplication extends VeneerApplication {
    /**
     * The constant NAME.
     */
    protected static final String NAME = "Resume Parser API";

    /**
     * Instantiates a new Jobs application.
     *
     * @param mappingIntrospector           the mapping introspector
     * @param inputJsonPropertyIntrospector the input json property introspector
     * @param applicationContext            the application context
     */
    @Autowired
    public ParsersApplication(@Lazy SkyhiveMappingIntrospector mappingIntrospector,
                              InputJsonPropertyIntrospector inputJsonPropertyIntrospector,
                              ApplicationContext applicationContext) {
        super(mappingIntrospector, inputJsonPropertyIntrospector, applicationContext);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ParsersApplication.class, args);
    }
}
