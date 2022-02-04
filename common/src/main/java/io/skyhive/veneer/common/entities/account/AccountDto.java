package io.skyhive.veneer.common.entities.account;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * The type Account dto.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
@Getter
@Setter
public class AccountDto {
    private String id;

    private String email;


    private LocalDateTime registrationDate;

    private LocalDateTime lastActivity;


    private String seoUrl;

    private String name;

    private String location;

    private boolean isInternal;
}
