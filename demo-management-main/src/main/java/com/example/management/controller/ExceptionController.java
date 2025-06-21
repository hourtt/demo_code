package com.example.management.controller;

import jakarta.validation.UnexpectedTypeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {UnexpectedTypeException.class, MethodArgumentNotValidException.class})
    protected String handleValidationExceptions(MethodArgumentNotValidException ex, Model model) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> {
                            errors.put(error.getField(), error.getDefaultMessage());
                        }
                );

        model.addAttribute("errors", errors);
        return "error";
    }
}
