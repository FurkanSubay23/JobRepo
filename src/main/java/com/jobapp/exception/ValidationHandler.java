package com.jobapp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error -> {//ex.getBindingResult().getAllErrors() çağrısı, MethodArgumentNotValidException nesnesinden geçersiz argüman hatalarını alır.
            String fieldName = ((FieldError) error).getField(); //  atanın alan adı fieldName olarak alınır
            String message = error.getDefaultMessage();         //  hata mesajı message olarak alınır.
            erros.put(fieldName, message);
        }));


        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(ErrorHandling exc) {   // INTEGER değerler için not found handler method.
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Object Not Found !!!", System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleExceptionAll(Exception e) {      // Integere gelmesi gereken yerlere String değerler atarsak mesela bad request olur.
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
