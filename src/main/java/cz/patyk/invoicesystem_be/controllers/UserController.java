package cz.patyk.invoicesystem_be.controllers;

import cz.patyk.invoicesystem_be.dto.in.UserDtoIn;
import cz.patyk.invoicesystem_be.dto.in.UserDtoInTwoPassword;
import cz.patyk.invoicesystem_be.dto.in.UserPasswordChangeIn;
import cz.patyk.invoicesystem_be.dto.out.UserDtoOut;
import cz.patyk.invoicesystem_be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<CollectionModel<UserDtoOut>> getAll(
            @PageableDefault final Pageable pageable
    ) {
        List<UserDtoOut> userDtoOutList = userService.getAll(pageable);
        userDtoOutList.forEach(paymentTypeDto -> paymentTypeDto.add(
                WebMvcLinkBuilder.linkTo(UserController.class)
                        .slash(paymentTypeDto.getId())
                        .withSelfRel()
        ));

        return ResponseEntity.ok(CollectionModel.of(userDtoOutList).add(
                WebMvcLinkBuilder.linkTo(
                                WebMvcLinkBuilder.methodOn(UserController.class)
                                        .getAll(pageable)
                        )
                        .withSelfRel()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoOut> getOne(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(userService.getOne(id));
    }

    @PostMapping("")
    public ResponseEntity<UserDtoOut> newItem(
            @RequestBody UserDtoInTwoPassword userDtoInTwoPassword
    ) {
        return ResponseEntity.ok(userService.newItem(userDtoInTwoPassword));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDtoOut> editItem(
            @RequestBody UserDtoIn userDtoIn,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(userService.editItem(userDtoIn, id));
    }

    @PutMapping("/password-change/{id}")
    public ResponseEntity<UserDtoOut> editItem(
            @RequestBody UserPasswordChangeIn userPasswordChangeIn,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(userService.passwordChange(userPasswordChangeIn, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable Long id
    ) {
        userService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
