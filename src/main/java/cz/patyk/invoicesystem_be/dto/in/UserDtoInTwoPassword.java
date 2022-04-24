package cz.patyk.invoicesystem_be.dto.in;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserDtoInTwoPassword extends UserDtoIn {
    @NonNull
    private String password;
    @NonNull
    private String retypePassword;
}
