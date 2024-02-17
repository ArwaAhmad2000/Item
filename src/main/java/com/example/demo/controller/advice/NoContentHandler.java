package com.example.demo.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.demo.controller.advice.helper.ErrorResponse;
import com.example.demo.service.exception.NoContentException;

@ControllerAdvice
public class NoContentHandler {

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<ErrorResponse> handleException(NoContentException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));

    }
}
