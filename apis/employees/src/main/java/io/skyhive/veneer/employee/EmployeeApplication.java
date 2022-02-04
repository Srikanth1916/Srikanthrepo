package io.skyhive.veneer.employee;

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
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The type Employee application.
 */
@SpringBootApplication(scanBasePackages = "io.skyhive.veneer", exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class
})
@OpenAPIDefinition(info =
@Info(title = "Employee API", version = "1.0", description = "Documentation Employee API v1.0")
)
@EnableFeignClients(basePackages = {"io.skyhive.veneer.common.rest"})
@EnableJpaRepositories("io.skyhive.veneer.common")
@EntityScan("io.skyhive.veneer.common.entities")
@PropertySources({
        @PropertySource("classpath:application-common.yml"),
        @PropertySource("classpath:application-prod.yml")
})
@EnableCaching
@EnableElasticsearchRepositories("io.skyhive.veneer")
public class EmployeeApplication extends VeneerApplication {
    /**
     * The constant NAME.
     */
    protected static final String NAME = "Employees API";

    /**
     * Instantiates a new Employee application.
     *
     * @param mappingIntrospector           the mapping introspector
     * @param inputJsonPropertyIntrospector the input json property introspector
     * @param applicationContext            the application context
     */
    @Autowired
    public EmployeeApplication(@Lazy SkyhiveMappingIntrospector mappingIntrospector,
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
        SpringApplication.run(EmployeeApplication.class, args);
    }
}
