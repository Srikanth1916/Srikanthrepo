package io.skyhive.veeneer.docs.parser;

import io.skyhive.veeneer.docs.merger.OpenApiMerger;
import io.skyhive.veeneer.docs.props.ServiceProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class DocsParser {
    @Autowired
    private ServiceProperties services;
    private OpenApiMerger merger = new OpenApiMerger();

    public void merge() {
        services.getUrls().forEach(url -> {
            RestTemplate restTemplate = new RestTemplate();
            String resultYaml = restTemplate.getForObject(url.getUrl().trim(), String.class);
            SwaggerParseResult result =  new OpenAPIV3Parser().readContents(resultYaml);
            OpenAPI api = result.getOpenAPI();
            merger.merge(api, url.getPath().trim());
        });
    }
    public OpenAPI get() {
        return merger.get();
    }
}
