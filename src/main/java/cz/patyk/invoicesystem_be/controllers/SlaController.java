package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.in.SlaDtoIn;
import cz.patyk.invoicesystem_be.dto.out.SlaDtoOut;
import cz.patyk.invoicesystem_be.service.SlaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sla")
@RequiredArgsConstructor
public class SlaController implements CrudController<SlaDtoIn, SlaDtoOut> {
    private final SlaService slaService;

    @Override
    public ResponseEntity<CollectionModel<SlaDtoOut>> getAll(Pageable pageable) {

        List<SlaDtoOut> slaDtoOuts = slaService.getAll(pageable);

        slaDtoOuts.forEach(slaDtoOut -> slaDtoOut.add(WebMvcLinkBuilder.linkTo(SlaController.class)
                .slash(slaDtoOut.getId())
                .withSelfRel()
        ));

        return ResponseEntity.ok(CollectionModel.of(slaDtoOuts).add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(SlaController.class)
                                .getAll(pageable)
                ).withSelfRel()
        ));
    }

    @Override
    public ResponseEntity<SlaDtoOut> getOne(Long id) {
        return ResponseEntity.ok(slaService.getOne(id));
    }

    @Override
    public ResponseEntity<SlaDtoOut> newItem(SlaDtoIn dtoIn) {
        return ResponseEntity.ok(slaService.newItem(dtoIn));
    }

    @Override
    public ResponseEntity<SlaDtoOut> editItem(@RequestBody SlaDtoIn dtoIn, Long id) {
        return ResponseEntity.ok(slaService.editItem(dtoIn, id));
    }

    @Override
    public ResponseEntity<Void> deleteItem(Long id) {
        slaService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
