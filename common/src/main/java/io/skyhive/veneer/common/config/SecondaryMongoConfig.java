package io.skyhive.veneer.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author krishna
 * @created 23/12/21
 * @project skyhive-veeneer
 */
@Configuration
@EnableMongoRepositories(value = "io.skyhive.veneer.models", mongoTemplateRef =
        "secondaryMongoTemplate")
public class SecondaryMongoConfig extends AbstractMongoClientConfiguration {

    @Autowired
    @Qualifier("secondaryMongoProperties")
    private MongoProperties properties;
    @Override
    protected String getDatabaseName() {
        return properties.getDatabase();
    }

    @Override
    public boolean autoIndexCreation() {
        return properties.isAutoIndexCreation();
    }
}
