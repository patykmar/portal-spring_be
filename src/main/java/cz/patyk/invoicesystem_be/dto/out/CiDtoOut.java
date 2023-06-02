package cz.patyk.invoicesystem_be.dto.out;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class CiDtoOut extends RepresentationModel<CiDtoOut> {
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
