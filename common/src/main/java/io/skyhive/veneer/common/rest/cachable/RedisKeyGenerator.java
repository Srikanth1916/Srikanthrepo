package io.skyhive.veneer.common.rest.cachable;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * The type Redis key generator.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
public class RedisKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        return StringUtils.arrayToDelimitedString(params, "_");
    }
}
