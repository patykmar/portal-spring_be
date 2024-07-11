package cz.patyk.invoicesystem_be.dto.out;

import org.springframework.hateoas.RepresentationModel;

public abstract class AbstractDtoOut<D extends RepresentationModel<? extends D>> extends RepresentationModel<D> {
    public abstract D getId();
}
