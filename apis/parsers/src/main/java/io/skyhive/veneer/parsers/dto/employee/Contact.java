package io.skyhive.veneer.parsers.dto.employee;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Contact.
 *
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
@Setter
@Getter
@Builder
public class Contact {
    private List<String> email;
    private List<String> phone;
}

