package io.skyhive.veneer.models.repository;

import io.skyhive.veneer.models.mongo.candidate.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author krishna
 * @created 23/12/21
 * @project skyhive-veeneer
 */
@Repository
public interface CandidateRepository extends MongoRepository<Candidate,
        String> {
    void findByIdAndEnterpriseId();
}
