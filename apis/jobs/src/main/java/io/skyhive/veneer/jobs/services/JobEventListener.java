package io.skyhive.veneer.jobs.services;

import io.skyhive.veneer.jobs.mapper.JobMapper;
import io.skyhive.veneer.models.mongo.job.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;

/**
 * @author krishna
 * @created 23/12/21
 * @project skyhive-veeneer
 */
public class JobEventListener extends AbstractMongoEventListener<Job> {
    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private JobEsService jobEsService;

    @Override
    public void onAfterSave(AfterSaveEvent<Job> event) {
        io.skyhive.veneer.models.es.job.Job job =
                jobMapper.toEsJob(event.getSource());
        jobEsService.save(job);
    }
}
