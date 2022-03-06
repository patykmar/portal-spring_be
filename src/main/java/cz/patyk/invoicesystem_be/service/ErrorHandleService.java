package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.exceptions.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class ErrorHandleService {

    public ApplicationException handleNotFoundError(Long id, String message) {
        log.error("ID: {} of {}", id, message);
        return new ApplicationException(message, HttpStatus.NOT_FOUND);
    }
}
