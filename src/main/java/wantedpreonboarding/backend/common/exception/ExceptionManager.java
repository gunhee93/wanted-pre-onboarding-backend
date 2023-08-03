package wantedpreonboarding.backend.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wantedpreonboarding.backend.common.code.ErrorCode;
import wantedpreonboarding.backend.common.response.ErrorResponse;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(CustomIllegalArgumentException.class)
    protected ResponseEntity<ErrorResponse> handleCustomIllegalArgumentException(CustomIllegalArgumentException e) {
        log.error("CustomIllegalArgumentException", e);

        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse response = ErrorResponse.from(errorCode);

        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

    @ExceptionHandler(CustomIllegalStateException.class)
    protected ResponseEntity<ErrorResponse> handleCustomIllegalStateException(CustomIllegalStateException e) {
        log.error("CustomIllegalStateException", e);

        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse response = ErrorResponse.from(errorCode);

        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

    private static String extractErrorReason(MethodArgumentNotValidException e) {
        return e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList())
                .get(0);
    }
}
