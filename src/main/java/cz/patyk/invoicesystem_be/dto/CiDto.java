package cz.patyk.invoicesystem_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class CiDto {
    private Long id;
    private Long parentId;
    private Long userId;
    private Long stateId;
    private Long tariffId;
    private Long companyId;
    private String name;
    private Date createdDateTime;
    private String description;
    private Long queueTier1Id;
    private Long queueTier2Id;
    private Long queueTier3Id;
}
