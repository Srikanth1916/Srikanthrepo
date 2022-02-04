package io.skyhive.veneer.employee.es;

import io.skyhive.veneer.models.es.candidate.Candidate;
import lombok.Getter;
import lombok.Setter;

/**
 * @author krishna
 * @created 23/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class CandidateWithScore extends Candidate {
    private float quality;
}
