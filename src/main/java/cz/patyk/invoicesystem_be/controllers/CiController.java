package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.in.CiDtoIn;
import cz.patyk.invoicesystem_be.dto.out.CiDtoOut;
import cz.patyk.invoicesystem_be.service.CiService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ci")
@RequiredArgsConstructor
public class CiController implements CrudController<CiDtoIn, CiDtoOut> {
    private final CiService ciService;

    @Override
    public ResponseEntity<CollectionModel<CiDtoOut>> getAll(Pageable pageable) {
        List<CiDtoOut> ciDtoOutList = ciService.getAll(pageable);
        ciDtoOutList.forEach(ciDtoOut -> ciDtoOut.add(WebMvcLinkBuilder.linkTo(CiController.class)
                .slash(ciDtoOut.getId())
                .withSelfRel()
        ));

        return ResponseEntity.ok(CollectionModel.of(ciDtoOutList)
                .add(WebMvcLinkBuilder.linkTo(
                                WebMvcLinkBuilder.methodOn(CiController.class)
                                        .getAll(pageable))
                        .withSelfRel()));
    }

    @Override
    public ResponseEntity<CiDtoOut> getOne(Long id) {
        return ResponseEntity.ok(ciService.getOne(id));
    }

    @Override
    public ResponseEntity<CiDtoOut> newItem(CiDtoIn dtoIn) {
        return ResponseEntity.ok(ciService.newItem(dtoIn));
    }

    @Override
    public ResponseEntity<CiDtoOut> editItem(CiDtoIn dtoIn, Long id) {
        return ResponseEntity.ok(ciService.editItem(dtoIn, id));
    }

    @Override
    public ResponseEntity<Void> deleteItem(Long id) {
        ciService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
