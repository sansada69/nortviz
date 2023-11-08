package com.nortvis.demo.exception.handler;

import com.nortvis.demo.dto.ErrorResponse;
import com.nortvis.demo.exception.ForbiddenException;
import com.nortvis.demo.exception.InternalServerError;
import com.nortvis.demo.exception.NotFoundException;
import com.nortvis.demo.exception.MapperException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidUserException(NotFoundException notFoundException) {
        ErrorResponse response = new ErrorResponse(notFoundException.getMessage(), 204);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(value = MapperException.class)
    public ResponseEntity<ErrorResponse> handleMapperException(MapperException mapperException) {
        ErrorResponse response = new ErrorResponse(mapperException.getMessage(), 500);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbiddenException(ForbiddenException forbiddenException) {
        ErrorResponse response = new ErrorResponse(forbiddenException.getMessage(), 403);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = InternalServerError.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(InternalServerError internalServerError) {
        ErrorResponse response = new ErrorResponse(internalServerError.getMessage(), 500);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
