package cz.patyk.invoicesystem_be.dto.in;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CiDtoIn {
    private final Long id;
    private final Long parentCiId;
    private final Long createdUserId;
    private final Long stateId;
    private final Long tariffId;
    private final Long companyId;
    private final Long queueTier1;
    private final Long queueTier2;
    private final Long queueTier3;
    private final String name;
    private final Long createdDateTime;
    private final String description;
}
