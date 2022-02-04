package io.skyhive.veneer.jobs.services;

import io.skyhive.veneer.models.es.candidate.Candidate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author krishna
 * @created 16/12/21
 * @project skyhive-veeneer
 */
@Repository
public interface CandidateEsRepository extends ElasticsearchRepository<Candidate, String> {
}
