package io.skyhive.veeneer.docs;

import io.skyhive.veeneer.docs.parser.DocsParser;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "Facade API", version = "1.0", description = "Documentation Facade API v1.0")
)
public class DocsServerApplication {

    @Autowired
    private DocsParser docsParser;

    public static void main(String[] args) {
        SpringApplication.run(DocsServerApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
        docsParser.merge();
        return docsParser.get();
    }
}