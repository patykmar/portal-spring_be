package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.mapper.CiMapper;
import cz.patyk.invoicesystem_be.repositories.CiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CiServices {
    private final CiRepository ciRepository;
    private final CiMapper ciMapper;
    private final ErrorHandleService errorHandleService;


}
