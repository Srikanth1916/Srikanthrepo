package io.skyhive.veneer.models.repository;

import io.skyhive.veneer.models.mongo.job.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author krishna
 * @created 23/12/21
 * @project skyhive-veeneer
 */
@Repository
public interface JobRepository extends MongoRepository<Job, String> {
    Optional<Job> findById(String objectId);
}
