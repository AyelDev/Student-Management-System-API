package com.ayeldev.Student_Management_API.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdviceClass {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<?, ?> handleMethodArgumentException(MethodArgumentNotValidException exception) {
		Map<String, String> errorMap = new HashMap();
		exception.getBindingResult().getFieldErrors().stream().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}
	
    // @ExceptionHandler(ConstraintViolationException.class)
    // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // public Map<?, ?> handleConstraintViolationException(ConstraintViolationException exception) {
    //     Map<String, String> errorMap = new HashMap<>();
    //     exception.getConstraintViolations()
    //             .stream()
    //             .forEach(error -> {
    //                 errorMap.put(error.getPropertyPath().toString(), error.getMessage());
    //             });
    //     return errorMap;
    // }
}
