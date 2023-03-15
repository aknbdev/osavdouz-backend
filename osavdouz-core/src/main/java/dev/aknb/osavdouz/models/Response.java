package dev.aknb.osavdouz.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private Boolean success = false;
    private List<ErrorData> errors;
    private String message;
    private T data;

    private Response(Boolean success) { // Response with Boolean(Success or Fail)
        this.success = success;
    }

    private Response(T data, Boolean success) { // Success response with data
        this.success = success;
        this.data = data;
    }

    private Response(String message) { // Success response with message
        this.message = message;
        this.success = Boolean.TRUE;
    }

    private Response(T data, Boolean success, String message) { // Returns response with data and message
        this.data = data;
        this.success = success;
        this.message = message;
    }

    private Response(String message, String code) { // Error response with message and code
        this.success = Boolean.FALSE;
        this.errors = Collections.singletonList(new ErrorData(message, code));
    }

    private Response(List<ErrorData> errors) { // Error response with error data list
        this.success = false;
        this.errors = errors;
    }

    public static <E> Response<E> ok(E data) { // Returns success response to the client
        return new Response<>(data, true);
    }

    public static <E> Response<E> ok(E data, Boolean notMessage) { // Returns success response to the client without message
        return new Response<>(data, true);
    }

    public static <E> Response<E> ok(E data, String message) { // Returns success response to the client with message
        return new Response<>(data, true, message);
    }

    public static <E> Response<E> ok() { // Returns success response to client
        return new Response<>(true);
    }

    public static Response<String> ok(String message) { // Returns success response to client with message
        return new Response<>(message);
    }

    public static Response<ErrorData> error(List<ErrorData> errors) { // Returns error response to client with error list
        return new Response<>(errors);
    }
    public static Response<ErrorData> error(ErrorData error) { // Returns error response to client with error
        return new Response<>(Collections.singletonList(error));
    }
    public static Response<ErrorData> error(String message, String code) { // Returns error response to client with error message and code
        return new Response<>(message, code);
    }
}
