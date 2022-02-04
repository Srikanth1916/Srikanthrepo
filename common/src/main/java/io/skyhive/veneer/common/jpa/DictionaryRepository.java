package io.skyhive.veneer.common.jpa;

import io.skyhive.veneer.common.db.mongo.entity.DictionaryWithComplexity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author krishna
 * @created 21/12/21
 * @project skyhive-veeneer
 */
@Repository
public interface DictionaryRepository extends MongoRepository<DictionaryWithComplexity, String> {
 List<DictionaryWithComplexity> findByIdIn(Set<String> ids);
}
