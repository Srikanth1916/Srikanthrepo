package io.skyhive.veneer.employee.services;

import io.skyhive.veneer.models.es.job.Job;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface JobEsRepository extends ElasticsearchRepository<Job, String> {
}