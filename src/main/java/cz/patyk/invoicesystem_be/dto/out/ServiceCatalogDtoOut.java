package cz.patyk.invoicesystem_be.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Getter
@Setter
@Builder
public class ServiceCatalogDtoOut extends RepresentationModel<ServiceCatalogDtoOut> {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Long vat;
    private VatDtoOut vatDtoOut;
    private int estimateTimeDelivery;
    private int estimateTimeReaction;
    private boolean isDisable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ServiceCatalogDtoOut that = (ServiceCatalogDtoOut) o;
        return estimateTimeDelivery == that.estimateTimeDelivery &&
                estimateTimeReaction == that.estimateTimeReaction &&
                isDisable == that.isDisable &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(price, that.price) &&
                Objects.equals(vat, that.vat) &&
                Objects.equals(vatDtoOut, that.vatDtoOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, description, price, vat, vatDtoOut, estimateTimeDelivery, estimateTimeReaction, isDisable);
    }
}


