package cz.patyk.invoicesystem_be.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserPasswordChangeId {
    private Long id;
    private String password;
    private String reTypedPassword;
    private Long passwordChanged;
}
