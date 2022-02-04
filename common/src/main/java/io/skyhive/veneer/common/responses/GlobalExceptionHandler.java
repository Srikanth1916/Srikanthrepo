package io.skyhive.veneer.common.responses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoWriteException;
import feign.FeignException;
import io.skyhive.veneer.common.exception.LocationUnidentifiedException;
import io.skyhive.veneer.common.exception.NotFoundException;
import io.skyhive.veneer.common.exception.ResourceAccessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Global exception handler.
 */
@RestControllerAdvice(basePackages = "io.skyhive.veneer")
@Slf4j
public class GlobalExceptionHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public VeneerResponse handleNotFoundException(NotFoundException ex,
                                                  WebRequest webRequest) {
        return new VeneerResponse.VeneerResponseBuilder().status(ex.getMessage()).build();
    }

    @ExceptionHandler(LocationUnidentifiedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public VeneerResponse handleLocationUnidentifiedException(LocationUnidentifiedException ex,
                                                  WebRequest webRequest) {
        return new VeneerResponse.VeneerResponseBuilder().status(ex.getMessage()).build();
    }

    @ExceptionHandler(ResourceAccessException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public VeneerResponse handleResourceNotAccessableException(ResourceAccessException ex, WebRequest request) {
        return  new VeneerResponse.VeneerResponseBuilder().status(ex.getMessage()).build();
    }

    /**
     * Handle validation exception veneer response.
     *
     * @param e the exception
     * @param response the response
     * @return the veneer response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public VeneerResponse handleValidationException(MethodArgumentNotValidException e, HttpServletResponse response) {
        List<Error> errors = e.getBindingResult()
                .getFieldErrors().stream().map(error -> {
                    Error e1 = new Error();
                    e1.setField(error.getField());
                    e1.setErrorMessages(Arrays.asList(error.getDefaultMessage()));
                    return e1;
                }).collect(Collectors.toList());
        errors.addAll(e.getBindingResult().getAllErrors().stream().map(objectError -> {Error e1 = new Error();
            e1.setErrorMessages(Arrays.asList(objectError.getDefaultMessage()));
            return e1;}).collect(Collectors.toList()));

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return VeneerResponse.builder().errorType(ErrorType.ValidationError).validationErrors(errors).status("Error").message("Invalid input data").build();
    }

    /**
     * Handle feign status exception veneer response.
     *
     * @param e        the e
     * @param response the response
     * @return the veneer response
     * @throws JsonProcessingException the json processing exception
     */
    @ExceptionHandler(FeignException.BadRequest.class)
    public VeneerResponse handleFeignStatusException(FeignException e, HttpServletResponse response) throws JsonProcessingException {
        response.setStatus(e.status());
        return objectMapper.readValue(e.contentUTF8(), VeneerResponse.class);
    }

    /**
     * Handle un authorized exception veneer response.
     *
     * @param ex      the ex
     * @param request the request
     * @return the veneer response
     */
    @ExceptionHandler(FeignException.Unauthorized.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public VeneerResponse handleUnAuthorizedException(FeignException.FeignClientException ex, WebRequest request) {
        return new VeneerResponse.VeneerResponseBuilder().status("Unauthorized").build();
    }

    /**
     * Handle internal server exception veneer response.
     *
     * @param ex      the ex
     * @param request the request
     * @return the veneer response
     */
    @ExceptionHandler(FeignException.FeignServerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public VeneerResponse handleInternalServerException(FeignException.FeignServerException ex, WebRequest request) {
        log.error("Error in Feign call", ex);
        return new VeneerResponse.VeneerResponseBuilder().status(ex.getMessage()).build();
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public VeneerResponse handleMongoWriteException(Throwable ex,
                                                    WebRequest request) {
        return new VeneerResponse.VeneerResponseBuilder().status(ex.getMessage()).build();
    }

    @ExceptionHandler(MongoWriteException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public VeneerResponse handleMongoWriteException(MongoWriteException ex, WebRequest request) {
        return new VeneerResponse.VeneerResponseBuilder().status(ex.getMessage()).build();
    }

}
