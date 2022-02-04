package io.skyhive.veneer.models.mongo.candidate;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author krishna
 * @created 17/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CoursesOrCertificates {
    private Institution institution;
    private Certificate certificate;
    private LocalDateTime issuedDate;
    private LocalDateTime expiryDate;
}
