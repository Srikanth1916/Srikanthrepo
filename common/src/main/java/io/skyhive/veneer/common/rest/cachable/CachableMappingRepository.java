package io.skyhive.veneer.common.rest.cachable;

import io.skyhive.veneer.common.entities.VeneerMapping;
import io.skyhive.veneer.common.jpa.MappingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

/**
 * The type Cachable mapping repository.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
@Configuration
@CacheConfig(cacheNames = "veneer")
public class CachableMappingRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(CachableMappingRepository.class);
    @Autowired
    private MappingRepository mappingRepository;

    /**
     * Find by id veneer mapping.
     *
     * @param skyhiveId the skyhive id
     * @return the veneer mapping
     */
    @Cacheable(cacheNames = "jpa", keyGenerator = "keyGenerator")
    public VeneerMapping findById(String skyhiveId) {
        Optional<VeneerMapping> returnValue = mappingRepository.findById(skyhiveId);
        return returnValue.get();
    }

    /**
     * Find by type and external id veneer mapping.
     *
     * @param type       the type
     * @param externalId the external id
     * @return the veneer mapping
     */
    @Cacheable(cacheNames = "jpa", keyGenerator = "keyGenerator")
    public VeneerMapping findByTypeAndExternalId(String type, String externalId) {
        return mappingRepository.findByTypeAndExternalId(type, externalId);
    }

    /**
     * Save veneer mapping.
     *
     * @param mapping the mapping
     * @return the veneer mapping
     */
// TODO: Need to add values to cache
    public VeneerMapping save(VeneerMapping mapping) {
        return mappingRepository.save(mapping);
    }

    /**
     * Delete.
     *
     * @param mapping the mapping
     */
    @Caching(evict = {@CacheEvict(cacheNames = "jpa", key = "{#mapping.skyhiveId}"),
            @CacheEvict(cacheNames = "jpa", key = "{#mapping.type+'_'+#mapping.externalId}")})
    public void delete(VeneerMapping mapping) {
        mappingRepository.delete(mapping);
    }
}
