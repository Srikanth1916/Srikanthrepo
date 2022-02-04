package io.skyhive.veneer.parsers.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enum Email approved by.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
public enum EmailApprovedBy {
    /**
     * None email approved by.
     */
    None,
    /**
     * By email email approved by.
     */
    ByEmail,
    /**
     * By admin email approved by.
     */
    ByAdmin,
    /**
     * By express registration email approved by.
     */
    ByExpressRegistration,
    /**
     * By facebook email approved by.
     */
    ByFacebook,
    /**
     * By azure ad email approved by.
     */
    ByAzureAD,
    /**
     * By saml email approved by.
     */
    BySaml;

    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }
}
