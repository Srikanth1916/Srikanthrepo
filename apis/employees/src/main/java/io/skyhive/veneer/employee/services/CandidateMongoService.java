package io.skyhive.veneer.employee.services;

import io.skyhive.veneer.common.exception.NotFoundException;
import io.skyhive.veneer.models.mongo.candidate.Candidate;
import io.skyhive.veneer.models.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

/**
 * @author krishna
 * @created 23/12/21
 * @project skyhive-veeneer
 */
@Service
public class CandidateMongoService {
    private final CandidateRepository candidateRepository;
    @Autowired
    public CandidateMongoService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }
    public Candidate save(Candidate candidate) {
       return candidateRepository.save(candidate);
    }
    public boolean existsById(String id, String enterpriseId) throws NotFoundException {
       Candidate candidate = findById(id, enterpriseId);
       return !ObjectUtils.isEmpty(candidate);
    }
    public Candidate findById(String id,
                                        String enterpriseId) throws NotFoundException {
        Optional<Candidate> candidateOptional =
                candidateRepository.findById(id);
        if (candidateOptional.isPresent() && !ObjectUtils.isEmpty(enterpriseId)) {
            Candidate candidate = candidateOptional.get();
            if(enterpriseId.equals(candidate.getEnterpriseId())){
                return candidate;
            }
        }
        throw new NotFoundException("Given id not found");
    }
    public void delete(Candidate candidate){
        candidateRepository.delete(candidate);
    }
}
