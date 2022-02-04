package io.skyhive.veneer.parsers.dto.employee;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Country dictionary dto.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
@Getter
@Setter
public class CountryDictionaryDto extends DictionaryDto {
    private String twoLetterName;

    private String currencySymbol;

    private String currencyCode;

    private Double rateToUsd;
}

