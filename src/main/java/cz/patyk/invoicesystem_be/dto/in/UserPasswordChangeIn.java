package cz.patyk.invoicesystem_be.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserPasswordChangeIn {
    @NonNull
    private String oldPassword;
    @NonNull
    private String newPassword;
    @NonNull
    private String reTypedPassword;
}
