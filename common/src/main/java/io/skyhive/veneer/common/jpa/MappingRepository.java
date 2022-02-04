package io.skyhive.veneer.common.jpa;

import io.skyhive.veneer.common.entities.VeneerMapping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Mapping repository.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
@Repository
public interface MappingRepository extends CrudRepository<VeneerMapping, String> {
    /**
     * Find by type and external id veneer mapping.
     *
     * @param type       the type
     * @param externalId the external id
     * @return the veneer mapping
     */
    VeneerMapping findByTypeAndExternalId(String type, String externalId);
}