package cz.patyk.invoicesystem_be.dto.in;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDtoInTwoPassword extends UserDtoIn {
    @NotBlank
    private String password;
    @NotBlank
    private String retypePassword;
}
