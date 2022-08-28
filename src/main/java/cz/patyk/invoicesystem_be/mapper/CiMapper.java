package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.in.CiDtoIn;
import cz.patyk.invoicesystem_be.dto.out.CiDtoOut;
import cz.patyk.invoicesystem_be.entities.Ci;
import cz.patyk.invoicesystem_be.entities.Company;
import cz.patyk.invoicesystem_be.entities.GeneralState;
import cz.patyk.invoicesystem_be.entities.Queue;
import cz.patyk.invoicesystem_be.entities.Tariff;
import cz.patyk.invoicesystem_be.entities.User;
import cz.patyk.invoicesystem_be.repositories.CiRepository;
import cz.patyk.invoicesystem_be.service.CompanyService;
import cz.patyk.invoicesystem_be.service.GeneralStateService;
import cz.patyk.invoicesystem_be.service.QueueServices;
import cz.patyk.invoicesystem_be.service.TariffService;
import cz.patyk.invoicesystem_be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
@Mapper(componentModel = "spring")
public abstract class CiMapper {
    @Autowired
    protected CiRepository ciRepository;
    @Autowired
    protected UserService userService;
    @Autowired
    protected GeneralStateService generalStateService;
    @Autowired
    protected TariffService tariffService;
    @Autowired
    protected CompanyService companyService;
    @Autowired
    protected QueueServices queueServices;

    @Mapping(source = "parentCi.id", target = "parentCiId")
    @Mapping(source = "createdUser.id", target = "createdUserId")
    @Mapping(source = "state.id", target = "stateId")
    @Mapping(source = "tariff.id", target = "tariffId")
    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "queueTier1.id", target = "queueTier1")
    @Mapping(source = "queueTier2.id", target = "queueTier2")
    @Mapping(source = "queueTier3.id", target = "queueTier3")
    @Mapping(target = "createdDateTime", expression = "java(ci.getCreatedDateTime().getTime())")
    public abstract CiDtoOut toDtoOut(Ci ci);

    @Mapping(target = "createdDateTime", expression = "java(java.util.Date.from(java.time.Instant.ofEpochSecond(ciDtoIn.getCreatedDateTime())))")
    @Mapping(target = "queueTier1", expression = "java(getQueue(ciDtoIn.getQueueTier1()))")
    @Mapping(target = "queueTier2", expression = "java(getQueue(ciDtoIn.getQueueTier2()))")
    @Mapping(target = "queueTier3", expression = "java(getQueue(ciDtoIn.getQueueTier3()))")
    @Mapping(target = "parentCi", expression = "java(getCi(ciDtoIn.getParentCiId()))")
    @Mapping(target = "createdUser", expression = "java(getUser(ciDtoIn.getCreatedUserId()))")
    @Mapping(target = "state", expression = "java(getGeneralState(ciDtoIn.getStateId()))")
    @Mapping(target = "tariff", expression = "java(getTariff(ciDtoIn.getTariffId()))")
    @Mapping(target = "company", expression = "java(getCompany(ciDtoIn.getCompanyId()))")
    public abstract Ci toEntity(CiDtoIn ciDtoIn);

    protected Queue getQueue(Long id) {
        return queueServices.getOneEntity(id);
    }

    protected Ci getCi(Long id) {
        return ciRepository.findById(id).orElse(null);
    }

    protected User getUser(Long id) {
        return userService.getOneEntity(id);
    }

    protected GeneralState getGeneralState(Long id) {
        return generalStateService.getOneEntity(id);
    }

    protected Tariff getTariff(Long id) {
        return tariffService.getOneEntity(id);
    }

    protected Company getCompany(Long id) {
        return companyService.getOneEntity(id);
    }
}
