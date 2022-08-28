package cz.patyk.invoicesystem_be.dto.in;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class UserPasswordChangeIn {
    @NonNull
    private String oldPassword;
    @NonNull
    private String newPassword;
    @NonNull
    private String reTypedPassword;
}
