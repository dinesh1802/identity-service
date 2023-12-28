package com.ekart.identityservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.ekart.identityservice.util.Constants.ERROR_MESSAGE;

@RestControllerAdvice
public class ApplicationExceptionHandler {


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidAccessException.class)
    public Map<String,String> invalidAccessExceptionHandler(InvalidAccessException ex){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put(ERROR_MESSAGE,ex.getMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String,String> errorMap=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return errorMap;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IdentityServiceException.class)
    public Map<String,String> identityServiceExceptionHandler(IdentityServiceException ex){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put(ERROR_MESSAGE,ex.getMessage());
        return errorMap;
    }

}
