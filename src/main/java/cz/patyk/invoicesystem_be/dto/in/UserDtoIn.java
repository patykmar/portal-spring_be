package cz.patyk.invoicesystem_be.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoIn {
    private Long id;
    private Long employeeOfCompanyId;
    private String email;
    private String firstName;
    private String lastName;
    private String roles;
}
