package io.skyhive.veneer.employee.dto.search;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author krishna
 * @created 27/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class EmployeeSearchResponse {
    private long total;
    private List<EmployeeWithScore> values;
}
