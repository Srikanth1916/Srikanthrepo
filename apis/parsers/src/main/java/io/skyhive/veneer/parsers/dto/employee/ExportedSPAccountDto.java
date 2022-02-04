package io.skyhive.veneer.parsers.dto.employee;

import java.time.LocalDateTime;

import io.skyhive.veneer.parsers.enums.ExportState;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Exported sp account dto.
 *
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
@Getter
@Setter
public class ExportedSPAccountDto {
    private LocalDateTime exportDate;
    private LocalDateTime registerDate;
    private LocalDateTime activationDate;
    private LocalDateTime deactivationDate;
    private ExportState exportState;

    // if user missed an export attempt, track how many times exporting has been attempted on user
    private int numPreviousExportAttempts;
}
