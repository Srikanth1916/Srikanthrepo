package io.skyhive.veneer.employee.mapper;

import io.skyhive.veneer.employee.dto.Employee;
import io.skyhive.veneer.employee.dto.search.EmployeeWithScore;
import io.skyhive.veneer.employee.es.CandidateWithScore;
import io.skyhive.veneer.models.mongo.candidate.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author krishna
 * @created 17/12/21
 * @project skyhive-veeneer
 */
@Mapper(uses = LocationMapper.class)
public interface EmployeeMapper {
    @Mappings({
            @Mapping(source = "employeeId", target = "id"),
            @Mapping(source = "accountInformation.email", target = "email")
    }
    )
    Candidate fromEsEmployee(Employee employee);
    io.skyhive.veneer.models.es.candidate.Candidate fromMongoCandidate(Candidate candidate);
    @Mappings({
            @Mapping(source = "id", target = "employeeId"),
            @Mapping(source = "email", target = "accountInformation.email"),
    }
    )
    Employee toDtoEmployee(Candidate candidate);

    @Mappings({
            @Mapping(source = "id", target = "employeeId"),
            @Mapping(source = "email", target = "accountInformation.email"),
    }
    )
    @Mapping(source = "quality", target = "quality", qualifiedByName =
            "CalculatePercentage")
    EmployeeWithScore toDtoEmployee(CandidateWithScore candidate);
    CandidateWithScore toCandidateWithScore(io.skyhive.veneer.models.es.candidate.Candidate candidate);
    @Named("CalculatePercentage")
    default double calculatePercentage(double quality) {
        double truncatedDouble = BigDecimal.valueOf(quality)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
        return truncatedDouble;
    }
}
