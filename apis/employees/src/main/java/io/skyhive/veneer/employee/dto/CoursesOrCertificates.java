package io.skyhive.veneer.employee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.skyhive.veneer.common.annotation.CompareDate;
import io.skyhive.veneer.common.serializer.MultiLocalDateTimeDeSerializer;
import io.skyhive.veneer.common.serializer.SkyhiveLocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

/**
 * The type Courses or certificates.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@CompareDate(before = "issuedDate", after="expiryDate", message = "issued " +
        " date must be lower than the expiry date")
public class CoursesOrCertificates {
    @Schema(description = "Name of the Institute in which employee has take course")
    private Institution institution;
    @Schema(description = "certificate of course studied by the employee")
    private Certificate certificate;
    @JsonDeserialize(using = MultiLocalDateTimeDeSerializer.class)
    @JsonSerialize(using = SkyhiveLocalDateTimeSerializer.class)
    @Schema(description = "Date of certificate issued for the course studied by the employee")
    @PastOrPresent(message = "Issued date cannot be future date")
    private LocalDateTime issuedDate;
    @JsonDeserialize(using = MultiLocalDateTimeDeSerializer.class)
    @JsonSerialize(using = SkyhiveLocalDateTimeSerializer.class)
    @Schema(description = "Date of expiry for certificate issued")
    private LocalDateTime expiryDate;


}
