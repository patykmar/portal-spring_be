package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.in.TariffDtoIn;
import cz.patyk.invoicesystem_be.dto.out.TariffDtoOut;
import cz.patyk.invoicesystem_be.entities.Tariff;
import cz.patyk.invoicesystem_be.entities.Vat;
import cz.patyk.invoicesystem_be.repositories.VatRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {VatMapper.class})
public abstract class TariffMapper {

    @Autowired
    protected VatRepository vatRepository;

    public abstract Tariff toEntity(TariffDtoIn tariffDtoIn);

    @Mapping(target = "vat", source = "vat.id")
    @Mapping(target = "vatDto", source = "tariff.vat")
    public abstract TariffDtoOut toDtoOut(Tariff tariff);

    public Vat getVat(Long vat) {
        return vatRepository.getById(vat);
    }
}
