package dev.aknb.osavdouz.exceptions;

import dev.aknb.osavdouz.response.ErrorData;
import dev.aknb.osavdouz.response.Response;
import dev.aknb.osavdouz.resolver.MessageResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ExceptionHelper extends ResponseEntityExceptionHandler {
    private final MessageResolver messageResolver;

    public ExceptionHelper(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    @ExceptionHandler(value = {RestException.class})
    public ResponseEntity<Response<ErrorData>> handleException(RestException exception) {

        String message = messageResolver.getMessage(exception.getCode(), exception.getArgs());

        if (exception.getFieldName() != null) {

            log.error("{} {} : '{}'", message, exception.getFieldName(), exception.getFieldValue());
            return ResponseEntity
                    .status(exception.getStatus())
                    .body(Response.error(message, exception.getCode()));
        }

        log.error("{}", message);
        return ResponseEntity
                .status(exception.getStatus())
                .body(Response.error(message, exception.getCode()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<FieldError> errors = ex.getFieldErrors();
        List<ErrorData> errorData = errors.stream()
                .map(error ->
                        new ErrorData(messageResolver.getMessage(error.getDefaultMessage()),
                                error.getDefaultMessage())
                ).collect(Collectors.toList());
        return handleExceptionInternal(ex, errorData, headers, HttpStatus.BAD_REQUEST, request);
    }
}
