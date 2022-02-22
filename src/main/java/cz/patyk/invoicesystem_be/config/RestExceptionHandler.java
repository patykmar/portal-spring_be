package cz.patyk.invoicesystem_be.config;

import cz.patyk.invoicesystem_be.dto.ErrorDto;
import cz.patyk.invoicesystem_be.exceptions.ApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {ApplicationException.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(ApplicationException exception) {
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(ErrorDto.builder()
                        .message(exception.getMessage())
                        .build()
                );
    }
}
