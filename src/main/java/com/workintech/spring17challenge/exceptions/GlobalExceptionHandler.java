package com.workintech.spring17challenge.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> handleException(ApiException apiException)
    {
        ApiErrorResponse errorResponse = new ApiErrorResponse(apiException.getHttpStatus().value(),
                apiException.getMessage(), System.currentTimeMillis());
        log.error("Exception occured: " + apiException);
        return new ResponseEntity<ApiErrorResponse>(errorResponse,apiException.getHttpStatus());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception exception)
    {
        ApiErrorResponse responseError = new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<ApiErrorResponse>(responseError,HttpStatus.BAD_REQUEST);
    }

}
