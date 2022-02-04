package io.skyhive.veneer.common.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The type Error.
 */
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {
    private String field;
    private List<String> errorMessages;
}
