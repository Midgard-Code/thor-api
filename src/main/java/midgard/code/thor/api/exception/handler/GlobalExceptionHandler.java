package midgard.code.thor.api.exception.handler;

import midgard.code.thor.api.exception.InternalServerException;
import midgard.code.thor.api.exception.InvalidRequestException;
import midgard.code.thor.api.model.result.ErrorResult;
import midgard.code.thor.api.model.result.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public ResponseEntity<Response<Void>> generateError(HttpStatus status, String message) {
        List<ErrorResult> errors = new ArrayList<>();
        errors.add(new ErrorResult(status.toString(), message));

        return ResponseEntity.status(status).body(new Response<>(errors));
    }

    @ExceptionHandler({InvalidRequestException.class})
    public ResponseEntity<Response<Void>> handleInvalidRequest(Exception e) {
        return generateError(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler({InternalServerException.class})
    public ResponseEntity<Response<Void>> handleInternalServer(Exception e) {
        return generateError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
