package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.exceptions.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ErrorHandleService {
    public ApplicationException handleNotFoundError(Long id, String message) {
        return handleBadRequestError(id, message, HttpStatus.NOT_FOUND);
    }

    public ApplicationException handleBadRequestError(Long id, String message) {
        return handleBadRequestError(id, message, HttpStatus.BAD_REQUEST);
    }

    public ApplicationException handleBadRequestError(String message) {
        log.error("{}", message);
        return new ApplicationException(message, HttpStatus.BAD_REQUEST);
    }

    private ApplicationException handleBadRequestError(Long id, String message, HttpStatus httpStatus) {
        log.error("ID: {} of {}", id, message);
        return new ApplicationException(message, httpStatus);
    }
}
