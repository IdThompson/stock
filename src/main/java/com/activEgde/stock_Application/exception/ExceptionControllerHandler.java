package com.activEgde.stock_Application.exception;

import com.activEgde.stock_Application.dto.response.ApiResponse;
import com.activEgde.stock_Application.utils.ApiUtil;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> userNotFound(NotFoundException exception){
        return ApiUtil.errorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse> customException(CustomException exception){
        return ApiUtil.errorResponse(HttpStatus.FORBIDDEN, exception.getMessage());
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse> badRequestExpection(BadRequestException exception) {
        return ApiUtil.errorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

}
