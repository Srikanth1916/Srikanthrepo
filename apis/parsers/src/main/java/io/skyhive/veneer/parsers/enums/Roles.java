package io.skyhive.veneer.parsers.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enum Roles.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive-veeneer
 */
public enum Roles {
    /**
     * None roles.
     */
    None,
    /**
     * Superadmin roles.
     */
    Superadmin,
    /**
     * Charity manager roles.
     */
    CharityManager,
    /**
     * Enterprise superadmin roles.
     */
    EnterpriseSuperadmin,
    /**
     * Support roles.
     */
    Support,
    /**
     * Creator roles.
     */
    Creator,
    /**
     * Admin roles.
     */
    Admin,
    /**
     * Creator admin roles.
     */
    CreatorAdmin,
    /**
     * Worker roles.
     */
    Worker,
    /**
     * Applicant roles.
     */
    Applicant,
    /**
     * Individual employer roles.
     */
    IndividualEmployer,
    /**
     * Company employer roles.
     */
    CompanyEmployer,
    /**
     * Charity roles.
     */
    Charity,
    /**
     * Enterprise company employer roles.
     */
    EnterpriseCompanyEmployer,
    /**
     * Enterprise owner roles.
     */
    EnterpriseOwner,
    /**
     * Enterprise hr roles.
     */
    EnterpriseHr,
    /**
     * Enterprise user roles.
     */
    EnterpriseUser,
    /**
     * Enterprise multiadmin roles.
     */
    EnterpriseMultiadmin,
    /**
     * Enterprise groupadmin roles.
     */
    EnterpriseGroupadmin,
    /**
     * Enterprise manager roles.
     */
    EnterpriseManager,
    /**
     * Archived roles.
     */
    Archived,
    /**
     * Display only roles.
     */
    DisplayOnly;

    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }
}

