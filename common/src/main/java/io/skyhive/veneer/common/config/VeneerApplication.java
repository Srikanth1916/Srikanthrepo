package io.skyhive.veneer.common.config;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import io.skyhive.veneer.common.annotation.InputJsonPropertyIntrospector;
import io.skyhive.veneer.common.annotation.SkyhiveMappingIntrospector;
import io.skyhive.veneer.common.rest.cachable.RedisKeyGenerator;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.SpringHandlerInstantiator;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.Collections;

/**
 * The type Veneer application.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
public abstract class VeneerApplication {
    /**
     * The constant NAME.
     */
    @Value("${esHost}")
    private String esHost;
    @Value("${esUsername}")
    private String esUserName;
    @Value("${esPassword}")
    private String esPassword;

    protected static final String NAME = "Veneer";
    private final SkyhiveMappingIntrospector mappingIntrospector;
    private final InputJsonPropertyIntrospector inputJsonPropertyIntrospector;
    private final ApplicationContext applicationContext;

    /**
     * Instantiates a new Veneer application.
     *
     * @param mappingIntrospector           the mapping introspector
     * @param inputJsonPropertyIntrospector the input json property introspector
     * @param applicationContext            the application context
     */
    public VeneerApplication(@Lazy SkyhiveMappingIntrospector mappingIntrospector,
                             @Lazy InputJsonPropertyIntrospector inputJsonPropertyIntrospector,
                             ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.mappingIntrospector = mappingIntrospector;
        this.inputJsonPropertyIntrospector = inputJsonPropertyIntrospector;
    }

    /**
     * Custom open api open api.
     *
     * @param appVersion the app version
     * @return the open api
     */
    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI()
                .components(new Components())
                .info(new io.swagger.v3.oas.models.info.Info().title(NAME).version(appVersion)
                        .license(new License().name("Apache 2.0").url("http" +
                                "://springdoc.org")));
    }

    /**
     * Cors configurer web mvc configurer.
     *
     * @return the web mvc configurer
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowedOrigins("*");
            }
        };
    }

    /**
     * Handler instantiator handler instantiator.
     *
     * @param context the context
     * @return the handler instantiator
     */
    @Bean
    public HandlerInstantiator handlerInstantiator(ApplicationContext context) {
        return new SpringHandlerInstantiator(context.getAutowireCapableBeanFactory());
    }

    /**
     * Object mapper builder jackson 2 object mapper builder.
     *
     * @param handlerInstantiator the handler instantiator
     * @return the jackson 2 object mapper builder
     */
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder(HandlerInstantiator handlerInstantiator) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.handlerInstantiator(handlerInstantiator);
        builder.failOnUnknownProperties(false);
        builder.failOnEmptyBeans(false);
        builder.findModulesViaServiceLoader(true);
        builder.dateFormat(new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss"));
        AnnotationIntrospector customIntrospectors = new AnnotationIntrospectorPair(mappingIntrospector, inputJsonPropertyIntrospector);
        builder.annotationIntrospector(new AnnotationIntrospectorPair(customIntrospectors, new JacksonAnnotationIntrospector()));
        builder.applicationContext(applicationContext);
        return builder;
    }

    /**
     * Key generator key generator.
     *
     * @return the key generator
     */
    @Bean
    @Lazy
    public KeyGenerator keyGenerator() {
        return new RedisKeyGenerator();
    }

    /**
     * Cache manager customizer cache manager customizer.
     *
     * @return the cache manager customizer
     */
    @Bean
    @Lazy
    public CacheManagerCustomizer<ConcurrentMapCacheManager> cacheManagerCustomizer() {
        return cacheManager -> {
            cacheManager.setAllowNullValues(false);
            cacheManager.setCacheNames(Collections.singleton("veneer"));
        };
    }

    /**
     * Gets redis template.
     *
     * @param objectMapper           the object mapper
     * @param redisConnectionFactory the redis connection factory
     * @return the redis template
     */
    @Bean
    @Lazy
    public RedisTemplate getRedisTemplate(ObjectMapper objectMapper, RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    @Lazy
    public RestHighLevelClient elasticClient(){
        final CredentialsProvider credentialsProvider =
                new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(esUserName, esPassword));

        RestClientBuilder builder = RestClient.builder(HttpHost.create(esHost))
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
                        .setDefaultCredentialsProvider(credentialsProvider));
        return new RestHighLevelClient(builder);
    }
}
