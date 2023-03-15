package dev.aknb.osavdouz.models;

import jakarta.annotation.Nullable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RestException extends RuntimeException {
    private HttpStatus status;
    private String fieldName;
    private Object fieldValue;
    private Object[] args;
    private String code;

    private RestException(String fieldName, Object fieldValue, String code, Object[] args) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.status = HttpStatus.BAD_REQUEST;
        this.code = code;
        this.args = args;
    }

    private RestException(String code, @Nullable HttpStatus status) {
        this.status = status;
        this.code = code;
    }

    private RestException(String code, HttpStatus status, Object[] args) {
        this.status = status;
        this.args = args;
        this.code = code;
    }

    public static RestException restThrow(HttpStatus status, String code, String... args) {
        return new RestException(code, status, args);
    }

    public static RestException restThrow(String fieldName, Object fieldValue, String code, String... args) {
        return new RestException(fieldName, fieldValue, code, args);
    }
}
