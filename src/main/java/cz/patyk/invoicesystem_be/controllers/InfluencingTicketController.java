package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.InfluencingTicketDto;
import cz.patyk.invoicesystem_be.service.InfluencingTicketService;
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
@RequestMapping("/api/influencing-tickets")
public class InfluencingTicketController {
    private final InfluencingTicketService influencingTicketService;

    @GetMapping("")
    public ResponseEntity<CollectionModel<InfluencingTicketDto>> getAll(
//            @RequestParam(name = "pageable", required = false, defaultValue = PageRequest.of(0, 10))
            @PageableDefault() final Pageable pageable
    ) {
        List<InfluencingTicketDto> influencingTicketDtos = influencingTicketService.getAll(pageable);

        influencingTicketDtos.forEach(influencingTicketDto -> influencingTicketDto.add(
                linkTo(InfluencingTicketController.class)
                        .slash(influencingTicketDto.getId())
                        .withSelfRel()
        ));
        return ResponseEntity.ok(CollectionModel.of(influencingTicketDtos).add(
                linkTo(methodOn(InfluencingTicketController.class).getAll(pageable))
                        .withSelfRel()
        ));
    }

    @GetMapping("/priorities")
    public ResponseEntity<CollectionModel<InfluencingTicketDto>> getAllForPriority(
            @PageableDefault() final Pageable pageable
    ) {
        List<InfluencingTicketDto> influencingTicketDtos = influencingTicketService.getAllForPriority(pageable);

        influencingTicketDtos.forEach(influencingTicketDto -> influencingTicketDto.add(
                linkTo(InfluencingTicketController.class)
                        .slash(influencingTicketDto.getId())
                        .withSelfRel()
        ));
        return ResponseEntity.ok(CollectionModel.of(influencingTicketDtos).add(
                linkTo(methodOn(InfluencingTicketController.class).getAllForPriority(pageable))
                        .withSelfRel()
        ));
    }

    @GetMapping("/impacts")
    public ResponseEntity<CollectionModel<InfluencingTicketDto>> getAllForImpact(
            @PageableDefault() final Pageable pageable
    ) {
        List<InfluencingTicketDto> influencingTicketDtos = influencingTicketService.getAllForImpact(pageable);

        influencingTicketDtos.forEach(influencingTicketDto -> influencingTicketDto.add(
                linkTo(InfluencingTicketController.class)
                        .slash(influencingTicketDto.getId())
                        .withSelfRel()
        ));
        return ResponseEntity.ok(CollectionModel.of(influencingTicketDtos).add(
                linkTo(methodOn(InfluencingTicketController.class).getAllForImpact(pageable))
                        .withSelfRel()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfluencingTicketDto> getOne(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(influencingTicketService.getOne(id));
    }

    @PostMapping("")
    public ResponseEntity<InfluencingTicketDto> newItem(
            @RequestBody InfluencingTicketDto influencingTicketDto
    ) {
        return ResponseEntity.ok(
                influencingTicketService.newItem(influencingTicketDto)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfluencingTicketDto> editItem(
            @RequestBody InfluencingTicketDto influencingTicketDto,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                influencingTicketService.editItem(influencingTicketDto, id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        influencingTicketService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
