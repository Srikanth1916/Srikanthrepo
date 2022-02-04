package io.skyhive.veneer.jobs.services;

import io.skyhive.veneer.common.exception.NotFoundException;
import io.skyhive.veneer.models.mongo.job.Job;
import io.skyhive.veneer.models.repository.JobRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

/**
 * @author krishna
 * @created 20/12/21
 * @project skyhive-veeneer
 */
@Transactional
@Service
@Slf4j
public class JobMongoService {
    private final JobRepository jobRepository;
    @Autowired
    public JobMongoService(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    public Job save(Job job){
        return jobRepository.save(job);
    }

    public void delete(Job job){
        jobRepository.delete(job);
    }

    public boolean existsById(String id, String enterpriseId) throws NotFoundException {
        Job job = findById(id, enterpriseId);
        return !ObjectUtils.isEmpty(job);
    }

    public Job  findById(String id, String enterpriseId) throws NotFoundException {
       Optional<Job> jobOptional =  jobRepository.findById(id);
       Job job = jobOptional.get();
       if(enterpriseId.equals(job.getEnterpriseId())) {
           return job;
       }
       throw new NotFoundException("Given JobId does not exists");
    }
}
