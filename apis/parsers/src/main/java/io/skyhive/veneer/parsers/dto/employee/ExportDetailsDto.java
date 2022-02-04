package io.skyhive.veneer.parsers.dto.employee;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Export details dto.
 *
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class ExportDetailsDto {
    private ExportedSPAccountDto accountDetails;
    private ExportedProfileDto profileDetails;
}
