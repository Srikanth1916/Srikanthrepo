package io.skyhive.veneer.common.exception;

/**
 * @author krishna
 * @created 25/01/22
 * @project skyhive-veeneer
 */
public class ResourceAccessException extends RuntimeException {
    public ResourceAccessException(String message){
        super(message);
    }
}
