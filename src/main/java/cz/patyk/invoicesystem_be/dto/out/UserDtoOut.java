package cz.patyk.invoicesystem_be.dto.out;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class UserDtoOut extends RepresentationModel<UserDtoOut> {
    private final Long id;
    private final Long employeeOfCompanyId;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final Long lastLogin;
    private final Long createdDate;
    private final Long passwordChanged;
    private final String roles;
}
