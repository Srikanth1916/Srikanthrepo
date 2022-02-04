package io.skyhive.veneer.parsers.dto.employee;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * The type License dto.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
@Getter
@Setter
public class LicenseDto {

    private NonValidableDictionaryDto institution;

    private DictionaryDto certificate;

    private LocalDateTime issuedDate;

    private LocalDateTime expiryDate;

    private boolean current;
}
