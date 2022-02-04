package io.skyhive.veneer.employee.mapper;

import io.skyhive.veneer.employee.es.EmployeeSearchRequest;
import io.skyhive.veneer.employee.es.EmployeeSearchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author krishna
 * @created 23/12/21
 * @project skyhive-veeneer
 */
@Mapper(uses = EmployeeMapper.class)
public interface EmployeeSearchMapper {
    @Mapping(target = "radius", source = "location.radius")
    EmployeeSearchRequest toEsEmployeeSearchRequest(io.skyhive.veneer.employee.dto.search.EmployeeSearchRequest employeeSearchRequest);
    io.skyhive.veneer.employee.dto.search.EmployeeSearchResponse toDtoEmployeeSearchResponse(EmployeeSearchResponse employeeSearchResponse);
}
