package cz.patyk.invoicesystem_be.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDtoIn extends RepresentationModel<UserDtoIn> {
    private Long id;
    private Long employeeOfCompanyId;
    private String email;
    private String firstName;
    private String lastName;
    private String roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserDtoIn userDtoIn = (UserDtoIn) o;
        return Objects.equals(id, userDtoIn.id) && Objects.equals(employeeOfCompanyId, userDtoIn.employeeOfCompanyId) && Objects.equals(email, userDtoIn.email) && Objects.equals(firstName, userDtoIn.firstName) && Objects.equals(lastName, userDtoIn.lastName) && Objects.equals(roles, userDtoIn.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, employeeOfCompanyId, email, firstName, lastName, roles);
    }
}
