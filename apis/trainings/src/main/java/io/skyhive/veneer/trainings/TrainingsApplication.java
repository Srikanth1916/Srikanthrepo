package io.skyhive.veneer.trainings;

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
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The type Training application.
 * 
 * @author jayaram
 * @created 02 /12/21
 * @project skyhive -veeneer
 */
@SpringBootApplication(scanBasePackages = "io.skyhive.veneer", exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class
})
@OpenAPIDefinition(info =
@Info(title = "Trainings API", version = "1.0", description = "Documentation Trainings API v1.0")
)
@PropertySources({
        @PropertySource("classpath:application-common.yml"),
        @PropertySource("classpath:application-prod.yml")
})
@EnableCaching
public class TrainingsApplication extends VeneerApplication {
    /**
     * The constant NAME.
     */
    protected static final String NAME = "Training API";

    /**
     * Instantiates a new Training application.
     *
     * @param mappingIntrospector           the mapping introspector
     * @param inputJsonPropertyIntrospector the input json property introspector
     * @param applicationContext            the application context
     */
    @Autowired
    public TrainingsApplication(@Lazy SkyhiveMappingIntrospector mappingIntrospector,
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
        SpringApplication.run(TrainingsApplication.class, args);
    }
}
