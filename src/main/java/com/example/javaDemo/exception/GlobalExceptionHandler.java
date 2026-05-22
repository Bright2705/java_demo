package com.example.javaDemo.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    @ResponseBody
    public Map<String, String> handleValidation(

            MethodArgumentNotValidException ex

    ) {

        Map<String, String> errors =
                new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {

                    errors.put(
                            error.getField(),
                            error.getDefaultMessage()
                    );

                });

        return errors;

    }

    @ExceptionHandler(
            UserNotFoundException.class
    )
    @ResponseBody
    public Map<String, String> handleUserNotFound(
            UserNotFoundException ex
    ) {
        Map<String, String> error =
                new HashMap<>();
        error.put(
                "message",
                ex.getMessage()
        );
        return error;
    }
}
