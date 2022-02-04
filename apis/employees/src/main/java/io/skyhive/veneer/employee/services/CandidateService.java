package io.skyhive.veneer.employee.services;

import io.skyhive.veneer.common.exception.NotFoundException;
import io.skyhive.veneer.models.es.candidate.Candidate;

/**
 * @author krishna
 * @created 21/12/21
 * @project skyhive-veeneer
 */
public interface CandidateService {
    Candidate save(Candidate can);
    boolean existsById(String id, String enterpriseId) throws NotFoundException;
}
