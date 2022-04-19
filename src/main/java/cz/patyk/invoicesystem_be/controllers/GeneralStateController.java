package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.GeneralStateDto;
import cz.patyk.invoicesystem_be.service.GeneralStateService;
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
@RequestMapping("/general-states")
public class GeneralStateController {
    private final GeneralStateService generalStateService;

    @GetMapping("")
    public ResponseEntity<CollectionModel<GeneralStateDto>> getAll(
            @PageableDefault final Pageable pageable
    ) {
        List<GeneralStateDto> generalStateDtoList = generalStateService.getAll(pageable);
        generalStateDtoList.forEach(generalStateDto -> generalStateDto.add(linkTo(GeneralStateController.class)
                .slash(generalStateDto.getId())
                .withSelfRel()
        ));

        return ResponseEntity.ok(CollectionModel.of(generalStateDtoList).add(
                linkTo(methodOn(GeneralStateService.class).getAll(pageable))
                        .withSelfRel()
        ));
    }

    @GetMapping("/for-ticket")
    public ResponseEntity<CollectionModel<GeneralStateDto>> getAllForTicket(
            @PageableDefault final Pageable pageable
    ) {
        List<GeneralStateDto> generalStateDtoList = generalStateService.getAllForTicket(pageable);
        generalStateDtoList.forEach(generalStateDto -> generalStateDto.add(linkTo(GeneralStateController.class)
                .slash(generalStateDto.getId())
                .withSelfRel()
        ));

        return ResponseEntity.ok(CollectionModel.of(generalStateDtoList).add(
                linkTo(methodOn(GeneralStateService.class).getAllForTicket(pageable))
                        .withSelfRel()
        ));
    }

    @GetMapping("/for-ci")
    public ResponseEntity<CollectionModel<GeneralStateDto>> getAllForCi(
            @PageableDefault final Pageable pageable
    ) {
        List<GeneralStateDto> generalStateDtoList = generalStateService.getAllForCi(pageable);
        generalStateDtoList.forEach(generalStateDto -> generalStateDto.add(linkTo(GeneralStateController.class)
                .slash(generalStateDto.getId())
                .withSelfRel()
        ));

        return ResponseEntity.ok(CollectionModel.of(generalStateDtoList).add(
                linkTo(methodOn(GeneralStateService.class).getAllForCi(pageable))
                        .withSelfRel()
        ));
    }

    @GetMapping("/for-close-state")
    public ResponseEntity<CollectionModel<GeneralStateDto>> getAllForClosedState(
            @PageableDefault final Pageable pageable
    ) {
        List<GeneralStateDto> generalStateDtoList = generalStateService.getAllForCloseState(pageable);
        generalStateDtoList.forEach(generalStateDto -> generalStateDto.add(linkTo(GeneralStateController.class)
                .slash(generalStateDto.getId())
                .withSelfRel()
        ));

        return ResponseEntity.ok(CollectionModel.of(generalStateDtoList).add(
                linkTo(methodOn(GeneralStateService.class).getAllForCloseState(pageable))
                        .withSelfRel()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralStateDto> getOne(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(generalStateService.getOne(id));
    }

    @PostMapping("")
    public ResponseEntity<GeneralStateDto> newItem(
            @RequestBody GeneralStateDto generalStateDto
    ) {
        return ResponseEntity.ok(generalStateService.newItem(generalStateDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralStateDto> editItem(
            @RequestBody GeneralStateDto generalStateDto,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(generalStateService.editItem(generalStateDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable Long id
    ) {
        generalStateService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
