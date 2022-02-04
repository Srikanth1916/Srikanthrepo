package io.skyhive.veneer.common.feign;

import feign.Response;

/**
 * The interface Feign http exception handler.
 *
 * @author krishna
 * @created 27 /10/21
 * @project skyhive -veeneer
 */
public interface FeignHttpExceptionHandler {
    /**
     * Handle exception.
     *
     * @param response the response
     * @return the exception
     */
    Exception handle(Response response);
}

