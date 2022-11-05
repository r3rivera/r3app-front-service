package com.ryanrivera.app.frontdesk.config;

import com.ryanrivera.app.frontdesk.exception.BaseAppException;
import com.ryanrivera.app.frontdesk.exception.ErrorResponse;
import com.ryanrivera.app.frontdesk.exception.UserCreationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ControllerConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserCreationException.class})
    public ResponseEntity<?> handleError(BaseAppException base){
        log.error("Error found of type user creation!");
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(base.getAppCode() + " :: " + base.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(status.value());
        return ResponseEntity.status(status).body(errorResponse);
    }




    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                 HttpHeaders headers,
                                 HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        //Get all fields errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);

    }

}
