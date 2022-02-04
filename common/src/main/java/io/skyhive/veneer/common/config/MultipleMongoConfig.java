package io.skyhive.veneer.common.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

/**
 * @author krishna
 * @created 23/12/21
 * @project skyhive-veeneer
 */
@Configuration
public class MultipleMongoConfig {
    @Primary
    @Bean(name ="primaryMongoProperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb")
    public MongoProperties getPrimary() {
        return new MongoProperties();
    }

    @Bean(name ="secondaryMongoProperties")
    @ConfigurationProperties(prefix = "mongodb")
    public MongoProperties getSecondary() {
        return new MongoProperties();
    }

    @Primary
    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate primaryMongoTemplate() {
        return new MongoTemplate(primaryMongoDatabaseFactory(getPrimary()));
    }
    @Bean(name = "secondaryMongoTemplate")
    public MongoTemplate secondaryMongoTemplate() {
        MongoTemplate mongoTemplate =
                new MongoTemplate(secondaryMongoDatabaseFactory(getSecondary()));
        return mongoTemplate;
    }
    @Primary
    @Bean
    public MongoDatabaseFactory primaryMongoDatabaseFactory(MongoProperties mongoProperties) {
        SimpleMongoClientDatabaseFactory simpleMongoClientDatabaseFactory = new SimpleMongoClientDatabaseFactory(
                mongoProperties.getUri()
        );
        return simpleMongoClientDatabaseFactory;
    }
    @Bean
    public MongoDatabaseFactory secondaryMongoDatabaseFactory(MongoProperties mongoProperties) {
        return new SimpleMongoClientDatabaseFactory(
                mongoProperties.getUri()
        );
    }
}
