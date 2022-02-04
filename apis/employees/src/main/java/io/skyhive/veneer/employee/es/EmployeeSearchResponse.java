package io.skyhive.veneer.employee.es;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author krishna
 * @created 27/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSearchResponse {
    private long total;
    private List<CandidateWithScore> values;
}
