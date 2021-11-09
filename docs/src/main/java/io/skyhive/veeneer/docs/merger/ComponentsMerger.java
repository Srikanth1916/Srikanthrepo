package io.skyhive.veeneer.docs.merger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.callbacks.Callback;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.links.Link;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityScheme;

public class ComponentsMerger {
    private Components components = new Components();
    private MapMerger<Schema> schemasMerger = new MapMerger();
    private MapMerger<ApiResponse> responsesMerger = new MapMerger<ApiResponse>();
    private MapMerger<Parameter> parametersMerger = new MapMerger<Parameter>();
    private MapMerger<Example> examplesMerger = new MapMerger<Example>();
    private MapMerger<RequestBody> requestBodiesMerger = new MapMerger<RequestBody>();
    private MapMerger<Header> headersMerger = new MapMerger<Header>();
    private MapMerger<SecurityScheme> securitySchemasMerger = new MapMerger<SecurityScheme>();
    private MapMerger<Link> linksMerger = new MapMerger<Link>();
    private MapMerger<Callback> callbacksMerger = new MapMerger<Callback>();

    public void merge(Components from) {
        if (from == null) return;
        schemasMerger.merge(from.getSchemas());
        responsesMerger.merge(from.getResponses());
        parametersMerger.merge(from.getParameters());
        examplesMerger.merge(from.getExamples());
        requestBodiesMerger.merge(from.getRequestBodies());
        headersMerger.merge(from.getHeaders());
        securitySchemasMerger.merge(from.getSecuritySchemes());
        linksMerger.merge(from.getLinks());
        callbacksMerger.merge(from.getCallbacks());
    }

    public Components get() {
        components.setSchemas(schemasMerger.get());
        components.setResponses(responsesMerger.get());
        components.setParameters(parametersMerger.get());
        components.setExamples(examplesMerger.get());
        components.setRequestBodies(requestBodiesMerger.get());
        components.setHeaders(headersMerger.get());
        components.setSecuritySchemes(securitySchemasMerger.get());
        components.setLinks(linksMerger.get());
        components.setCallbacks(callbacksMerger.get());
        return components;
    }

}
