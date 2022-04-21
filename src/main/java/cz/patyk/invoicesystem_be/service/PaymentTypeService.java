package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.PaymentTypeDto;
import cz.patyk.invoicesystem_be.entities.PaymentType;
import cz.patyk.invoicesystem_be.mapper.PaymentTypeMapper;
import cz.patyk.invoicesystem_be.repositories.PaymentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static cz.patyk.invoicesystem_be.service.ServiceConstants.PAYMENT_TYPE_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class PaymentTypeService {
    private final PaymentTypeRepository paymentTypeRepository;
    private final PaymentTypeMapper paymentTypeMapper;
    private final ErrorHandleService errorHandleService;

    public List<PaymentTypeDto> getAll(Pageable pageable) {
        return paymentTypeRepository.findAll(pageable)
                .stream()
                .map(paymentTypeMapper::toDto)
                .toList();
    }

    public PaymentTypeDto getOne(Long id) {
        return paymentTypeMapper.toDto(
                paymentTypeRepository.findById(id)
                        .orElseThrow(() -> errorHandleService.handleNotFoundError(id, PAYMENT_TYPE_NOT_FOUND_MESSAGE))
        );
    }

    public PaymentTypeDto newItem(PaymentTypeDto ticketTypeDto) {
        PaymentType paymentType = paymentTypeMapper.toEntity(ticketTypeDto);
        return paymentTypeMapper.toDto(paymentTypeRepository.save(paymentType));
    }

    public PaymentTypeDto editItem(PaymentTypeDto ticketTypeDto, Long id) {
        isIdExist(id);
        PaymentType paymentType = paymentTypeMapper.toEntity(ticketTypeDto);
        paymentType.setId(id);
        return paymentTypeMapper.toDto(paymentTypeRepository.save(paymentType));
    }

    public void deleteItem(Long id) {
        isIdExist(id);
        paymentTypeRepository.deleteById(id);
    }

    private void isIdExist(Long id) {
        if (!paymentTypeRepository.existsById(id)) {
            throw errorHandleService.handleNotFoundError(id, PAYMENT_TYPE_NOT_FOUND_MESSAGE);
        }
    }
}
