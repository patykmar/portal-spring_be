package cz.patyk.invoicesystem_be.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class CompanyDtoOut extends RepresentationModel<CompanyDtoOut> {
    private Long id;
    private String name;
    private String description;
    private String companyId;
    private String vatNumber;
    private String accountNumber;
    private String iban;
    private Long address;
    private AddressDtoOut addressDtoOut;
    private int created;
    private int modify;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CompanyDtoOut that = (CompanyDtoOut) o;
        return created == that.created && modify == that.modify && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(companyId, that.companyId) && Objects.equals(vatNumber, that.vatNumber) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(iban, that.iban) && Objects.equals(address, that.address) && Objects.equals(addressDtoOut, that.addressDtoOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, description, companyId, vatNumber, accountNumber, iban, address, addressDtoOut, created, modify);
    }
}