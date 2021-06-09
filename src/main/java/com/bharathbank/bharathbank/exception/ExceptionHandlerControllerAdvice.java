package com.bharathbank.bharathbank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest request){
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorMessage(exception.getMessage());
        response.setRequestURI(request.getRequestURI());
        response.setTimeStamp(new Date());
        //response.setTimeStamp(LocalDateTime.now());
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ExceptionResponse> insufficientBalanceException(final InsufficientBalanceException exception,HttpServletRequest request){
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorMessage(exception.getMessage());
        response.setRequestURI(request.getRequestURI());
        response.setTimeStamp(new Date());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
