    package io.skyhive.veneer.gateway.filter;

    import org.springframework.cloud.gateway.filter.GatewayFilter;
    import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
    import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
    import org.springframework.stereotype.Component;
    import org.springframework.web.client.ResourceAccessException;
    import reactor.core.publisher.Mono;

    import java.util.Arrays;
    import java.util.List;
    import java.util.Map;
    import java.util.Set;

    /**
     * @author krishna
     * @created 06/01/22
     * @project skyhive-veeneer
     */
    @Component
    public class AddEnterpriseHeaderGatewayFilterFactory extends AbstractGatewayFilterFactory<AddEnterpriseHeaderGatewayFilterFactory.Config> {
        public AddEnterpriseHeaderGatewayFilterFactory() {
            super(Config.class);
        }

        @Override
        public GatewayFilter apply(Config config) {
            GatewayFilter gatewayFilter =
                    (exchange, chain) -> exchange.getPrincipal().map(principal -> {
                        List<String> givenEnterpriseId = exchange.getRequest().getHeaders().getOrDefault("X-Enterprise-Id", null);
                        Map<String, Object> tokenAttributes = ((JwtAuthenticationToken) principal).getTokenAttributes();
                            String accountIds =
                                    (String) tokenAttributes
                                            .getOrDefault("X-Account-Id", null);
                          String enterpriseId =
                                    (String) tokenAttributes.getOrDefault("X-Enterprise-Id", null);
                            boolean isEnterpriseExists = checkEnterpriseExists(givenEnterpriseId == null ?
                                            Arrays.asList(enterpriseId) :
                                            givenEnterpriseId,
                                    accountIds);
                            if (isEnterpriseExists) {
                                exchange.getRequest().mutate().header(
                                                "X-Enterprise-Id", enterpriseId).header("X-Enterprise-Id-List", accountIds)
                                        .build();
                            } else {
                               throw new ResourceAccessException("Forbidden to access the resource");
                            }
                        return exchange;
                    }).flatMap(chain::filter).then(Mono.fromRunnable(() -> {
                    }));
            return gatewayFilter;
        }

        private boolean checkEnterpriseExists(List<String> enterpriseId, String accountIdString) {
            System.out.println("Enterprisi "+enterpriseId+"   "+accountIdString);
            if (enterpriseId != null && accountIdString != null) {
                String firstEnterpriseId = enterpriseId.get(0);
                String[] accountIdArray = accountIdString.split(",");
                Set<String> accountSet = Set.of(accountIdArray);
                if (accountSet.contains(firstEnterpriseId)) {
                    return true;
                }
            }
            return false;
        }

        public static class Config {
            public Config() {
            }
        }
    }
