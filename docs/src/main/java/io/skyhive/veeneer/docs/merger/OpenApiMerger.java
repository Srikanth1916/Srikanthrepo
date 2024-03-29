package io.skyhive.veeneer.docs.merger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;

public class OpenApiMerger {
    private OpenAPI openAPI = new OpenAPI();

    private ListMerger<Server> serversMerger = new ListMerger<Server>();
    private ListMerger<Tag> tagsMerger = new ListMerger<Tag>();
    private ListMerger<SecurityRequirement > securityMerger = new ListMerger<SecurityRequirement >();
    private PathsMerger pathsMerger = new PathsMerger();
    private ComponentsMerger componentsMerger = new ComponentsMerger();

    public void merge(OpenAPI from, String service){
        if(from ==null) return;
        serversMerger.merge(from.getServers());
        pathsMerger.merge(from.getPaths(), service);
        componentsMerger.merge(from.getComponents());
        securityMerger.merge(from.getSecurity());
        tagsMerger.merge(from.getTags());
    }
    public OpenAPI get() {
        openAPI.setServers(serversMerger.get());
        openAPI.setPaths(pathsMerger.get());
        openAPI.setComponents(componentsMerger.get());
        openAPI.setSecurity(securityMerger.get());
        openAPI.setTags(tagsMerger.get());
        return openAPI;
    }
}
