package io.skyhive.veneer.common.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

/**
 * The type Veneer response.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VeneerResponse {
    private String status;
    private ErrorType errorType;
    private String message;
    private List<Error> validationErrors;
}
