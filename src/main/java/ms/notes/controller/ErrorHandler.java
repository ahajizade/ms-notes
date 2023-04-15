package ms.notes.controller;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ms.notes.exception.ResourceNotFoundException;
import ms.notes.model.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    public static final String ERROR_MESSAGE_TEMPLATE = "Handling {}. Message: {}";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ExceptionResponse handle(Exception ex) {
        log.error(ERROR_MESSAGE_TEMPLATE, ex.getClass().getSimpleName(), ex.getMessage(), ex);
        return new ExceptionResponse("exception.unexpected");
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handle(ResourceNotFoundException ex) {
        return new ExceptionResponse(ex.getMessage());
    }

    @NonNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatus status,
                                                                  @NonNull WebRequest request) {
        log.warn(ERROR_MESSAGE_TEMPLATE, ex.getClass().getSimpleName(), ex.getMessage(), ex);
        List<ExceptionResponse> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> errors.add(new ExceptionResponse(e.getField() +
                " " + e.getDefaultMessage())));
        ex.getBindingResult().getGlobalErrors().forEach(e -> errors.add(new ExceptionResponse(e.getObjectName() +
                " " + e.getDefaultMessage())));
        return ResponseEntity.status(status).body(errors);
    }
}
