package io.skyhive.veneer.typeahead.services;

import io.skyhive.veneer.typeahead.mongo.Dictionary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * @author krishna
 * @created 12/01/22
 * @project skyhive-veeneer
 */
public interface DictionaryCrudRepository extends ReactiveCrudRepository<Dictionary,
        String> {
    @Query("{'Title.Title.en': {$regex: ?0, $options: 'i' }, 'Type': ?1, 'IsPublic': true}")
    Flux<Dictionary> findByQuery(String query, int type, Pageable pageable);
}
