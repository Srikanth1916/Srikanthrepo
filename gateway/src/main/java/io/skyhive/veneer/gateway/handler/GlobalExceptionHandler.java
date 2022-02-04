package io.skyhive.veneer.gateway.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;


/**
 * The type Global exception handler.
 */
@RestControllerAdvice(basePackages = "io.skyhive.veneer")
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceAccessException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public String handleResourceNotAccessableException(ResourceAccessException ex, WebRequest request) {
        return  "No Access permission for the requested resource";
    }
}
