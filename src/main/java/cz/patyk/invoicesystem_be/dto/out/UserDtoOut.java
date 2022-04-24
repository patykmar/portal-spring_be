package cz.patyk.invoicesystem_be.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDtoOut extends RepresentationModel<UserDtoOut> {
    private Long id;
    private Long employeeOfCompanyId;
    private String email;
    private String firstName;
    private String lastName;
    private Long lastLogin;
    private Long createdDate;
    private Long passwordChanged;
    private String roles;
}
