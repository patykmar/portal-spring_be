package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.constant.Common;
import cz.patyk.invoicesystem_be.constant.TestDtos;
import cz.patyk.invoicesystem_be.constant.TestEntities;
import cz.patyk.invoicesystem_be.dto.in.CompanyDtoIn;
import cz.patyk.invoicesystem_be.dto.out.CompanyDtoOut;
import cz.patyk.invoicesystem_be.entities.Company;
import cz.patyk.invoicesystem_be.repositories.AddressRepository;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CompanyMapperTest {
    private static final CompanyMapper companyMapper = Mappers.getMapper(CompanyMapper.class);

    @BeforeAll
    static void init() {
        AddressRepository addressRepository = Mockito.mock(AddressRepository.class);
        AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);
        ReflectionTestUtils.setField(companyMapper, "addressRepository", addressRepository);
        ReflectionTestUtils.setField(companyMapper, "addressMapper", addressMapper);
    }

    @ParameterizedTest
    @MethodSource("provideDto")
    void toEntity(CompanyDtoIn companyDtoIn) {
        Mockito
                .when(companyMapper.addressRepository.getById(companyDtoIn.getAddress()))
                .thenReturn(TestEntities.ADDRESS_TEST);

        assertThat(companyMapper.toEntity(companyDtoIn))
                .returns(companyDtoIn.getId(), Company::getId)
                .returns(companyDtoIn.getName(), Company::getName)
                .returns(companyDtoIn.getDescription(), Company::getDescription)
                .returns(companyDtoIn.getCompanyId(), Company::getCompanyId)
                .returns(companyDtoIn.getVatNumber(), Company::getVatNumber)
                .returns(companyDtoIn.getAccountNumber(), Company::getAccountNumber)
                .returns(companyDtoIn.getIban(), Company::getIban);
    }

    @ParameterizedTest
    @MethodSource("provideEntity")
    void toDto(Company company) {
        AddressMapper addressMapper = Mockito.mock(AddressMapper.class);
        CountryMapper countryMapper = Mockito.mock(CountryMapper.class);

        Mockito
                .when(addressMapper.toDto(company.getAddress()))
                .thenReturn(TestDtos.ADDRESS_TEST_DTO_OUT);

        Mockito
                .when(countryMapper.toDto(company.getAddress().getCountry()))
                .thenReturn(TestDtos.COUNTRY_DTO);
        ReflectionTestUtils.setField(companyMapper, "addressMapper", addressMapper);

        assertThat(companyMapper.toDto(company))
                .returns(company.getId(), CompanyDtoOut::getId)
                .returns(company.getName(), CompanyDtoOut::getName)
                .returns(company.getDescription(), CompanyDtoOut::getDescription)
                .returns(company.getCompanyId(), CompanyDtoOut::getCompanyId)
                .returns(company.getVatNumber(), CompanyDtoOut::getVatNumber)
                .returns(company.getAccountNumber(), CompanyDtoOut::getAccountNumber)
                .returns(company.getIban(), CompanyDtoOut::getIban);
    }

    private static Stream<Arguments> provideEntity() {
        return Stream.of(
                Arguments.of(new Company(Long.MIN_VALUE, Common.COMPANY_TEST_NAME, Common.COMPANY_TEST_DESCRIPTION, Common.COMPANY_TEST_COMPANY_ID, Common.COMPANY_TEST_VAT_NUMBER, Common.COMPANY_TEST_CREATED, Common.COMPANY_TEST_MODIFY, Common.COMPANY_TEST_ACCOUNT_NUMBER, Common.COMPANY_TEST_IBAN, List.of(), TestEntities.ADDRESS_TEST)),
                Arguments.of(new Company(NumberUtils.LONG_MINUS_ONE, Common.COMPANY_TEST_NAME, Common.COMPANY_TEST_DESCRIPTION, Common.COMPANY_TEST_COMPANY_ID, Common.COMPANY_TEST_VAT_NUMBER, Common.COMPANY_TEST_CREATED, Common.COMPANY_TEST_MODIFY, Common.COMPANY_TEST_ACCOUNT_NUMBER, Common.COMPANY_TEST_IBAN, List.of(), TestEntities.ADDRESS_TEST)),
                Arguments.of(new Company(NumberUtils.LONG_ZERO, Common.COMPANY_TEST_NAME, Common.COMPANY_TEST_DESCRIPTION, Common.COMPANY_TEST_COMPANY_ID, Common.COMPANY_TEST_VAT_NUMBER, Common.COMPANY_TEST_CREATED, Common.COMPANY_TEST_MODIFY, Common.COMPANY_TEST_ACCOUNT_NUMBER, Common.COMPANY_TEST_IBAN, List.of(), TestEntities.ADDRESS_TEST)),
                Arguments.of(new Company(NumberUtils.LONG_ONE, Common.COMPANY_TEST_NAME, Common.COMPANY_TEST_DESCRIPTION, Common.COMPANY_TEST_COMPANY_ID, Common.COMPANY_TEST_VAT_NUMBER, Common.COMPANY_TEST_CREATED, Common.COMPANY_TEST_MODIFY, Common.COMPANY_TEST_ACCOUNT_NUMBER, Common.COMPANY_TEST_IBAN, List.of(), TestEntities.ADDRESS_TEST)),
                Arguments.of(new Company(Long.MAX_VALUE, Common.COMPANY_TEST_NAME, Common.COMPANY_TEST_DESCRIPTION, Common.COMPANY_TEST_COMPANY_ID, Common.COMPANY_TEST_VAT_NUMBER, Common.COMPANY_TEST_CREATED, Common.COMPANY_TEST_MODIFY, Common.COMPANY_TEST_ACCOUNT_NUMBER, Common.COMPANY_TEST_IBAN, List.of(), TestEntities.ADDRESS_TEST))
        );
    }

    private static Stream<Arguments> provideDto() {
        return Stream.of(
                Arguments.of(new CompanyDtoIn(Long.MIN_VALUE, Common.COMPANY_TEST_NAME, Common.COMPANY_TEST_DESCRIPTION, Common.COMPANY_TEST_COMPANY_ID, Common.COMPANY_TEST_VAT_NUMBER, Common.COMPANY_TEST_ACCOUNT_NUMBER, Common.COMPANY_TEST_IBAN, NumberUtils.LONG_ONE)),
                Arguments.of(new CompanyDtoIn(NumberUtils.LONG_MINUS_ONE, Common.COMPANY_TEST_NAME, Common.COMPANY_TEST_DESCRIPTION, Common.COMPANY_TEST_COMPANY_ID, Common.COMPANY_TEST_VAT_NUMBER, Common.COMPANY_TEST_ACCOUNT_NUMBER, Common.COMPANY_TEST_IBAN, NumberUtils.LONG_ONE)),
                Arguments.of(new CompanyDtoIn(NumberUtils.LONG_ZERO, Common.COMPANY_TEST_NAME, Common.COMPANY_TEST_DESCRIPTION, Common.COMPANY_TEST_COMPANY_ID, Common.COMPANY_TEST_VAT_NUMBER, Common.COMPANY_TEST_ACCOUNT_NUMBER, Common.COMPANY_TEST_IBAN, NumberUtils.LONG_ONE)),
                Arguments.of(new CompanyDtoIn(NumberUtils.LONG_ONE, Common.COMPANY_TEST_NAME, Common.COMPANY_TEST_DESCRIPTION, Common.COMPANY_TEST_COMPANY_ID, Common.COMPANY_TEST_VAT_NUMBER, Common.COMPANY_TEST_ACCOUNT_NUMBER, Common.COMPANY_TEST_IBAN, NumberUtils.LONG_ONE)),
                Arguments.of(new CompanyDtoIn(Long.MAX_VALUE, Common.COMPANY_TEST_NAME, Common.COMPANY_TEST_DESCRIPTION, Common.COMPANY_TEST_COMPANY_ID, Common.COMPANY_TEST_VAT_NUMBER, Common.COMPANY_TEST_ACCOUNT_NUMBER, Common.COMPANY_TEST_IBAN, NumberUtils.LONG_ONE))
        );
    }
}