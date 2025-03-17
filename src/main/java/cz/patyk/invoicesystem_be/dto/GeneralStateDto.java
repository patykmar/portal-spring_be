package cz.patyk.invoicesystem_be.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class GeneralStateDto extends RepresentationModel<GeneralStateDto> {
    private Long id;
    private String name;
    private boolean isForTicket;
    private boolean isForCi;
    private boolean isForCloseState;
    private float coefficientPrice;
}
