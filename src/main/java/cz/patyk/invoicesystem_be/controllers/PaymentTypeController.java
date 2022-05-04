package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.PaymentTypeDto;
import cz.patyk.invoicesystem_be.dto.in.PaymentTypeDtoIn;
import cz.patyk.invoicesystem_be.service.PaymentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment-types")
public class PaymentTypeController {
    private final PaymentTypeService paymentTypeService;

    @GetMapping("")
    public ResponseEntity<CollectionModel<PaymentTypeDto>> getAll(
            @PageableDefault final Pageable pageable
    ) {
        List<PaymentTypeDto> paymentTypeDtoList = paymentTypeService.getAll(pageable);
        paymentTypeDtoList.forEach(paymentTypeDto -> paymentTypeDto.add(linkTo(PaymentTypeController.class)
                .slash(paymentTypeDto.getId())
                .withSelfRel()
        ));

        return ResponseEntity.ok(CollectionModel.of(paymentTypeDtoList).add(
                linkTo(methodOn(PaymentTypeController.class).getAll(pageable))
                        .withSelfRel()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentTypeDto> getOne(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(paymentTypeService.getOne(id));
    }

    @PostMapping("")
    public ResponseEntity<PaymentTypeDto> newItem(
            @Valid @RequestBody PaymentTypeDtoIn paymentTypeDto
    ) {
        return ResponseEntity.ok(paymentTypeService.newItem(paymentTypeDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentTypeDto> editItem(
            @RequestBody @Valid PaymentTypeDtoIn paymentTypeDtoIn,
            @PathVariable @Positive Long id
    ) {
        return ResponseEntity.ok(paymentTypeService.editItem(paymentTypeDtoIn, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable Long id
    ) {
        paymentTypeService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
