package io.skyhive.veeneer.docs.parser;

import io.skyhive.veeneer.docs.merger.OpenApiMerger;
import io.skyhive.veeneer.docs.props.ServiceProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DocsParser {
    private final OpenApiMerger merger = new OpenApiMerger();
    @Autowired
    private ServiceProperties services;

    public void merge() {
        services.getUrls().forEach(url -> {
            RestTemplate restTemplate = new RestTemplate();
            String resultYaml = restTemplate.getForObject(url.getUrl().trim(), String.class);
            SwaggerParseResult result = new OpenAPIV3Parser().readContents(resultYaml);
            OpenAPI api = result.getOpenAPI();
            merger.merge(api, url.getPath().trim());
        });
    }

    public OpenAPI get() {
        return merger.get();
    }
}
