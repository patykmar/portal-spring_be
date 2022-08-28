package cz.patyk.invoicesystem_be.controllers;

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

/**
 * Specify methods for basic CRUD operations
 * @param <I> input DTO object
 * @param <R> return DTO object
 */
public interface CrudController<I, R> {
    @GetMapping("")
    ResponseEntity<CollectionModel<R>> getAll(@PageableDefault final Pageable pageable);

    @GetMapping("/{id}")
    ResponseEntity<R> getOne(@PathVariable Long id);

    @PostMapping("")
    ResponseEntity<R> newItem(@RequestBody I dtoIn);

    @PutMapping("/{id}")
    ResponseEntity<R> editItem(@RequestBody I dtoIn, @PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteItem(@PathVariable Long id);
}
