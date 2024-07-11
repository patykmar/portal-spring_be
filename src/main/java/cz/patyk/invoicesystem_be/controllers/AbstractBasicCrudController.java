package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.out.AbstractDtoOut;
import cz.patyk.invoicesystem_be.service.CrudService;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class AbstractBasicCrudController<I, R extends AbstractDtoOut<R>, E> implements BasicCrudController<I, R> {
    protected CrudService<I, R, E> crudService;
    @Override
    public ResponseEntity<CollectionModel<R>> getAll(Pageable pageable) {
        List<R> dtoOuts = crudService.getAll(pageable);
        dtoOuts.forEach(dtoOut -> dtoOut.add(WebMvcLinkBuilder.linkTo(CiControllerBasic.class)
                .slash(dtoOut.getId())
                .withSelfRel()
        ));

        return ResponseEntity.ok(CollectionModel.of(dtoOuts)
                .add(WebMvcLinkBuilder.linkTo(
                                WebMvcLinkBuilder.methodOn(CiControllerBasic.class)
                                        .getAll(pageable))
                        .withSelfRel()));
    }

    @Override
    public ResponseEntity<R> getOne(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<R> newItem(I dtoIn) {
        return null;
    }

    @Override
    public ResponseEntity<R> editItem(I dtoIn, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteItem(Long id) {
        return null;
    }
}
