package io.skyhive.veneer.common.feign;

import feign.Response;
import feign.codec.Decoder;

import java.io.IOException;

/**
 * The type Employee service exception handler.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
public class EmployeeServiceExceptionHandler implements FeignHttpExceptionHandler {
    @Override
    public Exception handle(Response response) {
        try {
            Decoder.Default decoder = new Decoder.Default();
            String errorResponse = (String) decoder.decode(response, String.class);
            return new Exception(errorResponse);
        } catch (IOException ie) {
            return ie;
        }

    }
}
