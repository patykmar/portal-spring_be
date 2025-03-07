package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.QueueDto;
import cz.patyk.invoicesystem_be.service.QueueServices;
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
@RequestMapping("/api/queues")
public class QueueController {
    private final QueueServices queueServices;

    @GetMapping("")
    public ResponseEntity<CollectionModel<QueueDto>> getAll(
            @PageableDefault final Pageable pageable
    ) {
        List<QueueDto> queueDtos = queueServices.getAll(pageable);
        queueDtos.forEach(queueDto -> queueDto.add(linkTo(QueueController.class)
                .slash(queueDto.getId())
                .withSelfRel()
        ));

        return ResponseEntity.ok(CollectionModel.of(queueDtos).add(
                linkTo(methodOn(QueueController.class).getAll(pageable))
                        .withSelfRel()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QueueDto> getOne(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(queueServices.getOne(id));
    }

    @PostMapping("")
    public ResponseEntity<QueueDto> newItem(
            @RequestBody QueueDto queueDto
    ) {
        return ResponseEntity.ok(queueServices.newItem(queueDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QueueDto> editItem(
            @RequestBody QueueDto queueDto,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(queueServices.editItem(queueDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable Long id
    ) {
        queueServices.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
