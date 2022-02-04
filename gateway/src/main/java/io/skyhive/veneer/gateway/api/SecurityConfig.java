package io.skyhive.veneer.gateway.api;

import io.skyhive.veneer.gateway.filter.AddEnterpriseHeaderGatewayFilterFactory;
import io.skyhive.veneer.gateway.filter.KeycloakRealmRoleConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import reactor.core.publisher.Mono;

/**
 * @author krishna
 * @created 09/11/21
 * @project skyhive-veeneer
 */
@Configuration
@Slf4j
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.cors().and().csrf().disable().authorizeExchange().pathMatchers(
                "/docs",
                        "/docs/**", "/jobs/api-docs.yaml", "/employees/api" +
                                "-docs.yaml", "/trainings/api-docs.yaml",
                        "/location/api-docs.yaml",
                        "/careerpath/api-docs.yaml",
                        "/parsers/api-docs.yaml","/typeahead/**").permitAll()
                .and()
                .authorizeExchange()
                .pathMatchers(HttpMethod.GET, "/employees/*").hasRole("EMPLOYEE_READ")
                .pathMatchers(HttpMethod.GET, "/jobs/*").hasRole("JOB_READ")
                .pathMatchers(HttpMethod.POST, "/employees/**").hasAnyRole("EMPLOYEE_READ") // Search
                .pathMatchers(HttpMethod.POST, "/employees/*").hasAnyRole("EMPLOYEE_WRITE") // Update
                .pathMatchers(HttpMethod.PUT, "/employees/*").hasRole("EMPLOYEE_WRITE")
                .pathMatchers(HttpMethod.DELETE, "/employees/*").hasRole("EMPLOYEE_WRITE")
                .pathMatchers(HttpMethod.GET, "/careerpath/**").hasRole("CAREER_READ")
                .pathMatchers(HttpMethod.GET, "/location/*").hasRole("LOCATION_READ")
                .pathMatchers(HttpMethod.PUT, "/jobs/*").hasRole("JOB_WRITE")
                .pathMatchers(HttpMethod.POST, "/jobs/*").hasRole("JOB_WRITE")
                .pathMatchers(HttpMethod.DELETE, "/jobs/*").hasRole("JOB_WRITE")
                .pathMatchers(HttpMethod.POST, "/job/**").hasRole("JOB_READ")
                .pathMatchers("/parsers/**").hasRole("PARSER_READ")
                .anyExchange().authenticated()
                .and().oauth2ResourceServer().jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(jwtAuthenticationConverter()));
        return http.build();
    }

    private Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
        return new ReactiveJwtAuthenticationConverterAdapter(jwtConverter);
    }
}
