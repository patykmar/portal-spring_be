package cz.patyk.invoicesystem_be.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDtoIn {
    private Long id;
    private Long employeeOfCompanyId;
    private String email;
    private String firstName;
    private String lastName;
    private String roles;
}
