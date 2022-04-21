package cz.patyk.invoicesystem_be.dto;

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
public class UserDto extends RepresentationModel<UserDto> {
    private Long id;
    private Long employeeOfCompanyId;
    private String email;
    private String firstName;
    private String lastName;
    private Long lastLogin;
    private Long created;
    private Long passwordChanged;
    private String roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(employeeOfCompanyId, userDto.employeeOfCompanyId) && Objects.equals(email, userDto.email) && Objects.equals(firstName, userDto.firstName) && Objects.equals(lastName, userDto.lastName) && Objects.equals(lastLogin, userDto.lastLogin) && Objects.equals(created, userDto.created) && Objects.equals(passwordChanged, userDto.passwordChanged) && Objects.equals(roles, userDto.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, employeeOfCompanyId, email, firstName, lastName, lastLogin, created, passwordChanged, roles);
    }
}
