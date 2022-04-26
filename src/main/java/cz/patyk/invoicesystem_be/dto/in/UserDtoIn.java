package cz.patyk.invoicesystem_be.dto.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoIn {
    private Long id;
    private Long employeeOfCompanyId;
    private String email;
    private String firstName;
    private String lastName;
    private String roles;
}
