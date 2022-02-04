package io.skyhive.veneer.common.rest;

import io.skyhive.veneer.common.entities.account.LoginRequest;
import io.skyhive.veneer.common.entities.account.UserDto;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * The interface Skyhive login.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
@FeignClient(name = "auth", url = "${enterprise.api.url}")
@CacheConfig(cacheNames = "veneer")
public interface SkyhiveLogin {
    /**
     * Authenticate user user dto.
     *
     * @param request the request
     * @return the user dto
     */
    @PostMapping(value = "/api/v1.0/auth/login")
    @Cacheable(cacheNames = "login", keyGenerator = "keyGenerator")
    UserDto authenticateUser(LoginRequest request);

}
