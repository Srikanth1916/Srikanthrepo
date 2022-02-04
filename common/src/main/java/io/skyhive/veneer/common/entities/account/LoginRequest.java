package io.skyhive.veneer.common.entities.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The type Login request.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
@Getter
@AllArgsConstructor
public class LoginRequest {

    @JsonProperty("Email")
    private String email;
    @JsonProperty("Password")
    private String password;

    @Override
    public String toString() {
        return email;
    }
}
