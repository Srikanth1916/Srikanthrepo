package io.skyhive.veneer.jobs.services;

import io.skyhive.veneer.models.es.job.Job;
import org.bson.types.ObjectId;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author krishna
 * @created 16/12/21
 * @project skyhive-veeneer
 */
public interface JobEsRepository extends ElasticsearchRepository<Job,
        String> {
}

