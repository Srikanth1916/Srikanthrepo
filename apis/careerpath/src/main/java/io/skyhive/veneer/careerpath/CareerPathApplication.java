package io.skyhive.veneer.careerpath;

import io.skyhive.veneer.common.annotation.InputJsonPropertyIntrospector;
import io.skyhive.veneer.common.annotation.SkyhiveMappingIntrospector;
import io.skyhive.veneer.common.config.VeneerApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The type CareerPath application.
 * 
 * @author jayaram
 * @created 06/12/21
 * @project skyhive -veeneer
 */
@SpringBootApplication(scanBasePackages = "io.skyhive.veneer", exclude =
        {ElasticsearchRestClientAutoConfiguration.class})
@OpenAPIDefinition(info =
@Info(title = "Career Path API", version = "1.0", description = "Documentation Career Path API v1.0")
)
@EnableFeignClients(basePackages = {"io.skyhive.veneer.common.rest", "io" +
        ".skyhive.veneer.careerpath.rest"})
@EnableElasticsearchRepositories
@EnableJpaRepositories("io.skyhive.veneer.common")
@EntityScan("io.skyhive.veneer.common.entities")
@PropertySources({
        @PropertySource("classpath:application-common.yml"),
        @PropertySource("classpath:application-prod.yml")
})
public class CareerPathApplication extends VeneerApplication {
    /**
     * The constant NAME.
     */
    protected static final String NAME = "Career Path API";

    /**
     * Instantiates a new Career Path application.
     *
     * @param mappingIntrospector           the mapping introspector
     * @param inputJsonPropertyIntrospector the input json property introspector
     * @param applicationContext            the application context
     */
    @Autowired
    public CareerPathApplication(@Lazy SkyhiveMappingIntrospector mappingIntrospector,
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
        SpringApplication.run(CareerPathApplication.class, args);
    }
}
