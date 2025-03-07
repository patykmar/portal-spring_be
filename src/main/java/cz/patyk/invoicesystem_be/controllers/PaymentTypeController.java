package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.out.PaymentTypeDtoOut;
import cz.patyk.invoicesystem_be.dto.in.PaymentTypeDtoIn;
import cz.patyk.invoicesystem_be.service.PaymentTypeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
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

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment-types")
public class PaymentTypeController {
    private final PaymentTypeService paymentTypeService;

    @GetMapping("")
    public ResponseEntity<CollectionModel<PaymentTypeDtoOut>> getAll(
            @PageableDefault final Pageable pageable
    ) {
        List<PaymentTypeDtoOut> paymentTypeDtoOutList = paymentTypeService.getAll(pageable);
        paymentTypeDtoOutList.forEach(paymentTypeDto -> paymentTypeDto.add(linkTo(PaymentTypeController.class)
                .slash(paymentTypeDto.getId())
                .withSelfRel()
        ));

        return ResponseEntity.ok(CollectionModel.of(paymentTypeDtoOutList).add(
                linkTo(methodOn(PaymentTypeController.class).getAll(pageable))
                        .withSelfRel()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentTypeDtoOut> getOne(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(paymentTypeService.getOne(id));
    }

    @PostMapping("")
    public ResponseEntity<PaymentTypeDtoOut> newItem(
            @Valid @RequestBody PaymentTypeDtoIn paymentTypeDto
    ) {
        return ResponseEntity.ok(paymentTypeService.newItem(paymentTypeDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentTypeDtoOut> editItem(
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
