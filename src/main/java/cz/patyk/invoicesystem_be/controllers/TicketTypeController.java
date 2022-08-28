package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.TicketTypeDto;
import cz.patyk.invoicesystem_be.service.TicketTypeService;
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
@RequestMapping("/ticket-types")
public class TicketTypeController {
    private final TicketTypeService ticketTypeService;

    @GetMapping("")
    public ResponseEntity<CollectionModel<TicketTypeDto>> getAll(
            @PageableDefault() final Pageable pageable
    ) {
        List<TicketTypeDto> ticketTypeDtos = ticketTypeService.getAll(pageable);

        ticketTypeDtos.forEach(ticketTypeDto -> ticketTypeDto.add(
                linkTo(CompanyController.class)
                        .slash(ticketTypeDto.getId())
                        .withSelfRel()
        ));
        return ResponseEntity.ok(CollectionModel.of(ticketTypeDtos).add(
                linkTo(methodOn(CompanyController.class).getAll(pageable))
                        .withSelfRel()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketTypeDto> getOne(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(ticketTypeService.getOne(id));
    }

    @PostMapping("")
    public ResponseEntity<TicketTypeDto> newItem(
            @RequestBody TicketTypeDto ticketTypeDto
    ) {
        return ResponseEntity.ok(
                ticketTypeService.newItem(ticketTypeDto)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketTypeDto> editItem(
            @RequestBody TicketTypeDto ticketTypeDto,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                ticketTypeService.editItem(ticketTypeDto, id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        ticketTypeService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
