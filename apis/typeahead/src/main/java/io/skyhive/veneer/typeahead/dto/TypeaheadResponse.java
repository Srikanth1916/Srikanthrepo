package io.skyhive.veneer.typeahead.dto;

import io.skyhive.veneer.typeahead.mongo.Dictionary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * @author krishna
 * @created 12/01/22
 * @project skyhive-veeneer
 */
@Getter
@Setter
@AllArgsConstructor
public class TypeaheadResponse {
    private List<Dictionary> values;
}
